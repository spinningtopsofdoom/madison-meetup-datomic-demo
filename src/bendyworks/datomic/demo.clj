(ns bendyworks.datomic.demo
  (:require [datomic.api :as d]))

(def vdb
  [[1  :person/name  "Bob"                  42]
   [2  :person/name  "Martha"               42]
   [3  :person/name  "Fred"                 43]
   [4  :person/name  "Laura"                43]
   [1  :person/child 5                      43]
   [2  :person/child 5                      43]
   [5  :person/name  "Abe"                  43]
   [1  :person/child 6                      43]
   [2  :person/child 6                      43]
   [6  :person/name  "Susan"                44]
   [3  :person/child 7                      43]
   [4  :person/child 7                      43]
   [7  :person/name  "Bill"                 44]
   [7  :person/pet   8                      44]
   [8  :pet/name     "Fluffy the Terrible"  44]
   [5  :person/child 9                      45]
   [6  :person/child 9                      45]
   [9  :person/name  "Bluto"                45]
   [9  :person/pet   10                     45]
   [10 :pet/name     "Mr. Whiskers"         45]])

;; All pet name's
(d/q '[:find ?pet-name
       :where [?pet :pet/name ?pet-name]]
     vdb)

;; All pet's and owners
(d/q '[:find ?name ?pet-name
       :where [?person :person/name ?name]
              [?person :person/pet ?pet]
              [?pet :pet/name ?pet-name]]
     vdb)

;; All children
(d/q '[:find ?name
       :where [?child :person/name ?name]
              [_ :person/child ?child]]
     vdb)

;; All person id's
(d/q '[:find ?person
       :where [?person :person/name]]
     vdb)

;; All person attributes
(d/q '[:find ?attr
       :where [?person :person/name]
              [?person ?attr]]
     vdb)

;; All child relations when Laura was created
(d/q '[:find ?parent-name ?child-name
       :where [_ :person/name "Laura" ?tx]
              [?parent :person/child ?child ?tx]
              [?parent :person/name ?parent-name]
              [?child :person/name ?child-name]]
     vdb)

;; Database rules for recursive query
(def ancestors-rule
  '[[(ancestors ?parent ?child)
     [?parent :person/child ?child]]
    [(ancestors ?grand-parent ?parent)
     [?grand-parent :person/child ?child]
     (ancestors ?child ?parent)]])

;; Find all people who are ancestors
(d/q '[:find ?name
       :in $ %
       :where [?person :person/name]
              [ancestors ?ancestors ?person]
              [?ancestors :person/name ?name]]
     vdb ancestors-rule)

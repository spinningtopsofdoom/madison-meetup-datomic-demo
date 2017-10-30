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

;; Get all pet name's
(d/q '[:find ?pet-name
       :where [?pet :pet/name ?pet-name]]
     vdb)
;; Get all pet's and owners
(d/q '[:find ?name ?pet-name
       :where [?person :person/name ?name]
              [?person :person/pet ?pet]
              [?pet :pet/name ?pet-name]]
     vdb)
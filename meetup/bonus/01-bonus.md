# Bonus Round

!SLIDE

# Entities can be navigated like objects

    @@@clojure
    (def alice
      (d/entity
        (d/q '[:find ?person
               :where [?person :person/name "Alice"]]
          db)))

!SLIDE

# Get values just like an object

    @@@clojure
    (get alice :food/likes "mushrooms") ;; pizza
    (get alice :pet/name) ;; Mr Whiskers
    (get alice :foo/bar) ;; nil

!SLIDE

# Acts like a graph database

    @@@clojure
    ;; All the people alice likes
    (get alice :person/likes)

<br />

    @@@clojure
    ;; All the people that like alice
    (get alice :person/_likes)

!SLIDE

# Speculate on that a database would look like

    @@@clojure
    (def speculative
      (d/with alice-db
        {:db/id "bob"
        :person/name "Bob"
        :perons/likes "mac and cheese"}))

!SLIDE

# Speculative Data does not affect db

    @@@clojure
    ;; Gets the alice-db name's ("Alice")
    (d/q '[:find ?name
           :where [_ :person/name ?name]]
      alice-db)

<br />

    @@@clojure
    ;; Gets the speculative name's ("Alice", "Bob")
    (d/q '[:find ?name
           :where [_ :person/name ?name]]
      speculative)

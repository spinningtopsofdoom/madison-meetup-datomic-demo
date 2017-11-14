# Everything is Queryable

!SLIDE

## There's the everyday querying of entities (records) and attribute values.
### Symbols with `<?*>` are variables to be filled in by the query

    @@@clojure
    ;; Gets the Alice's entity identifier (12)
    (d/q '[:find ?person
           :where [?person :person/name "Alice"]]
      db)

<br />

    @@@clojure
    ;; Gets the name for entity with id of 12 ("Alice")
    (d/q '[:find ?name
           :where [12 :person/name ?name]]
      db)

!SLIDE

## You can also get all attributes of an entity

    @@@clojure
    ;; Gets the all attributes and values for entity with id of 12
    ;; ([:person/name "Alice"] [:person/likes "pizza"])
    (d/q '[:find ?attribute ?name
           :where [12 ?attribute ?name]]
      db)

!SLIDE

## `_`'s in a where clause means you don't care about the value

    @@@clojure
    ;; Gets the all attributes that have alice as a value (:person/name)
    (d/q '[:find ?attribute
           :where [_ ?attribute "Alice"]
        db)

!SLIDE

## All of the database attributes are queryable

    @@@clojure
    ;; Gets the all attributes in the database (even system level ones)
    (d/q '[:find ?attr-name
          :where [_ :db/ident ?attr-name]]
      db)

!SLIDE

## Joins happen when the same variable used in different clauses


    @@@clojure
    ;; Find what Alice likes
    (d/q '[:find ?alikes
           :where [?person :person/name "Alice"]
                  [?person :person/likes ?likes]
        db)

<br />

    @@@clojure
    ;; Gets the all attributes connected to
    ;; :person/name (:person/name :person/likes)
    (d/q '[:find ?attr-name
          :where [?reference :person/name]
                 [?reference ?attribute]
                 [?attribute :db/ident ?attr-name]]
      db)

!SLIDE

## Bonus: You can query plain data

    @@@clojure
    ;; Gets the all attributes and values for entity with id of 12
    ;; ([:person/name "Alice"] [:person/likes "pizza"])
    (d/q '[:find ?attribute ?name
           :where [12 ?attribute ?name]]
      [[12 :perons/name "Alice"]i
       [12 :person/likes "pizza"]])

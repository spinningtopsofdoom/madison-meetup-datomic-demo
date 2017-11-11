Schema is Ala Carte

!SLIDE

Typical entity (record) creation

    @@@clojure
    ;; Adds Alice entity to the database
    (d/transact connection [{:person/name  "Alice"
                             :person/likes "pizza"}])

!SLIDE

Datomic entity can contain any attributes or combination of attributes

    @@@clojure
    ;; Adds Alice name to the database
    (d/transact connection [{:person/name  "Alice"
                             :person/likes "pizza"}])

<br />

    @@@clojure
    ;; Adds Alice entity to the database
    (d/transact connection [{:person/name  "Alice"
                             :person/likes "pizza"
                             :person/age   34}])
<br />

    @@@clojure
    ;; Adds Alice entity and toppings to the database
    (d/transact connection [{:person/name    "Alice"
                             :person/likes   "pizza"
                             :pizza/toppings #{:pepperoni :olives}}])

!SLIDE

Entities (records) are not confined to a table row or a document. Attributes are the atom's of the schema

    @@@clojure
    (d/transact connection
      [{:db/ident       :person/name
        :db/valueType   :db.type/string
        :db/cardinality :db.cardinality/one
        :db/doc         "A persons's name"}])

!SLIDE

We get an easy incremental schema, incredible flexibility adding data to our database, and a rich query language.

!SLIDE

Bonus: Temporary entity references
`:db.id` assigns a temporary entity id that other entities in the transaction can use as a reference.

	@@@clojure
	(d/transact connection
      [{:db/id "bobid"
	    :person/name "Bob"
	    :person/spouse "aliceid"}
	   {:db/id "aliceid"
	    :person/name "Alice"
	    :person/spouse "bobid"}])

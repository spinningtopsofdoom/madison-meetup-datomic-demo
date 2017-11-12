Transactions are real and easily accessible

!SLIDE

Each transaction gives the transaction data

    @@@clojure
    (d/transact conn [{:person/name "Alice"
                       :person/likes "pizza"}])
    ;; Data added to the database from the transaction
    [[13194139534313 50 #inst "2017-11-02T20:24:08.492-00:00" 13194139534313 true]
     [17592186045418 63 "Alice" 13194139534313 true]
     [17592186045418 64 "pizza" 13194139534313 true]]

!SLIDE

Each transaction is marked in the database as a transaction id. We can retrieve all items added in a transaction by querying it `?tx`

	@@@clojure
	(d/q '[:find ?entity ?attr-name ?value
		   :where [_ :person/name "Alice" ?tx]
				  [?entity ?attribute ?value ?tx]
				  [?attribute :db/ident ?attr-name]]
		 db)
    ;; Returns
    [[17592186045418 :person/name "Alice"]
     [13194139534313 :db/txInstant #inst "2017-11-02T20:24:08.492-00:00"]
     [17592186045418 :person/likes "pizza"]]

!SLIDE

Arbitrary information can be added onto the transaction

	@@@clojure
	(d/transact conn [{:person/name "Alice"
					   :person/likes "pizza"}
					  {:db/id "datomic.tx"
					   :auditor/name "bob"}])

!SLIDE

We can retrieve this transaction information

	@@@clojure
	(d/q '[:find ?attr-name ?value
		   :where [_ :person/name "Alice" ?tx]
				  [?tx-id :db/txInstant _ ?tx]
				  [?tx-id ?attribute ?value]
				  [?attribute :db/ident ?attr-name]]
		 db)
    ;; Transaction information
    [[:auditor/name "bob"]
     [:db/txInstant #inst "2017-11-02T20:41:09.339-00:00"]]

!SLIDE

Transactions can be monitored

    @@@clojure
    ;; Create a transaction queue
    (def tx-queue
      (d/tx-report-queue connection))

<br />

    @@@clojure
    ;; Retrieves a transaction sent to the database
    (.poll tx-queue)

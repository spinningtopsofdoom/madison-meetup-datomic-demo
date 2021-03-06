# Datomic always growing

!SLIDE

## No data is ever removed from Datomic, it only accumulates data, never updating or erasing it

!SLIDE

## Does this mean we can never erase or update anything?

!SLIDE

## A new database is created for each transaction

    @@@clojure
    ;; Creates db-initial
    (d/transact connection
      [{:person/name "Alice"
        :person/likes "pizza"}])

<br />

	@@@clojure
    ;; Gets ("Alice" "pizza")
	(d/q '[:find ?name ?likes
		   :where [?person :person/name ?name]
				  [?person :person/likes ?likes]]
		 db-initial)

!SLIDE

## Changing a attribute value creates a new database


    @@@clojure
    ;; Creates db-salad
    (d/transact connection
      [{:db/id alice-id
        :person/likes "salad"}])

<br />

	@@@clojure
    ;; Gets ("Alice" "salad")
	(d/q '[:find ?name ?likes
		   :where [?person :person/name ?name]
				  [?person :person/likes ?likes]]
		 db-salad)

!SLIDE

## Removing an attribute value creates a new database

    @@@clojure
    ;; Creates db-no-likes
    (d/transact connection
      [:db/retract alice-id :person/likes "salad"])

<br />

	@@@clojure
    ;; Gets ()
	(d/q '[:find ?name ?likes
		   :where [?person :person/name ?name]
				  [?person :person/likes ?likes]]
		 db-no-likes)

!SLIDE

<div/>
# Starting Database

![Initial Database](../../images/datomic-initial.png)

!SLIDE

<div/>
# Salad Database

![Salad Database](../../images/datomic-salad.png)

!SLIDE

<div/>
# No Likes Database

![No Likes Database](../../images/datomic-no-likes.png)

!SLIDE

# Writes add facts never delete or overwrite

!SLIDE

<div/>
# Query Starting Database

![Initial Database Query](../../images/datomic-initial-query.png)

!SLIDE

<div/>
# Adding salad is orthogonal

![Salad Database](../../images/datomic-salad-query.png)

!SLIDE

<div/>
# Writes don't block reads

![No Likes Database](../../images/datomic-no-likes-query.png)


!SLIDE

# Reads

* Unlimited
* Stable
* Not blocked by writes
* Can run as long as needed

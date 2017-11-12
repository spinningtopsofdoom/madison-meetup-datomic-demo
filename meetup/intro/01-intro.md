<br />
<br />
<br />
<br />
# Datomic - The Immutable Database
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
## @spinningtopsofdoom / @bendyworks

!SLIDE

# what problems does Datomic solve?

!SLIDE

# Database annoyances

## Database - Postgres
## Web Framework - Ruby on Rails

!SLIDE

# Wiring up relationships
## Relationships work one way


    @@@sql
    SELECT i.*
    FROM items AS i
    JOIN line_items AS li ON li.item_id = i.id
    JOIN orders AS o ON o.id = li.order_id
    JOIN user AS u ON o.user_id = u.id
    WHERE u.email = "jane.doe@example.com"


!SLIDE

# Wiring Up Tests

    @@@shell
    Running rails rspec
    ...........................
    Tests took 38.10 minutes

!SLIDE

# Table Oriented Data Entry

!SLIDE

# Querying Via Strings

!SLIDE

# Needing to retrieve everything in one go

!SLIDE

# Lazy Loading vs eager loading

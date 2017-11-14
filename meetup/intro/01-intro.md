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

# Datomic Marketing

## - fully transactional
## - cloud-ready
## - distributed database.

!SLIDE

# What problems does Datomic solve?

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

# Lazy Load (N + 1) ^ M
## Eager load means constructing large query

    @@@ruby
    user.map(&:orders).flat_map(&:line_items).flat_map(&:items)

!SLIDE

# Table Oriented Structure

## `user` must have all fields in `Users` table
## Adding fields from other tables requires a relationship between the tables

!SLIDE

# Creating multiple related records
## ActiveRecord Needed

    @@@ruby
    eggs = Item.new(name: "eggs")
    bread = Item.new(name: "bread")
    line_items = LineItem.new(items: [eggs, bread])
    order = Order.new(user: user, line_items: line_items)
    order.save

!SLIDE

# Datomic Solutions

* Everything Queryable
* Ala carte Schema
* Always Grow
* Transactions Fully Realized

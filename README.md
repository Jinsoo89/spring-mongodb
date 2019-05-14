# MongoDB

## Contents
> * NoSQL Database types <br>
> * About MongoDB <br>
> * Performance advantage <br>
> * Document structures <br>
> * MongoDB CRUD Operations <br>
> * Spring Data MongoDB <br>

## NoSQL Database types
> * Document Databases: <br>
pair each key with a complex data structure which is called a document. Documents can contain key-value or key-array pairs or even nested documents. 
> * Key-value stores: <br>
every single item is stored as a key-value pair. The most simple way among NoSQL DBs.
> * Wide-column stores: <br>
store columns of data together, instead of rows, optimized for queries over large datasets.
> * Graph stores: <br>
used to store information about networks of data, such as social connections.

## About MongoDB: <br>
> * initially developed by 10gen SW company in 2007.
> * the company shifted to an open source development in 2009.
> * in 2013, 10gen changed its name to MongoDB Inc.
> * MongoDB became a publicly-traded company (IPO) in 2017.

### Performance advantage
> * Flexible Database <br> we can have any type of data in a separate document. This gives us flexibility to store data.
> * Sharding <br> we can store a large data by distributing it to several servers connected to the application. <br>
>![Image of Sharding](https://d2h0cx97tjks2p.cloudfront.net/blogs/wp-content/uploads/sites/2/2018/04/image-1-9-300x270.png)
> * Fast query response <br> it's easy to access documents by indexing.
> * Scalability <br> MongoDB is a horizontally scalable database, you can distribute a large data to several machines.
> * Ad-hoc Query support <br> MongoDB has an advanced feature for ad-hoc queries.

### [Document Structure](https://docs.mongodb.com/manual/core/data-modeling-introduction/)

> * Embedded Data (Denormalization) <br>
embedded documents capture relationships between data by storing related data in a single document structure. <br>
pros: this type provides better performance for query performance (read), request & retrieve related data in a single operation <br> 
cons: may cause data inconsistency.

```javascript
{
    _id: <ObjectId1>,
    username: "Jinsoo",
    contact: {
        // 이 부분이 embedded sub-document
        phone: "010-1234-4321",
        email: "hello.python89@wise-fashion.com"
            },
    access: {
        // 이 부분이 embedded sub-document
        level: 5,
        group: "dev"
            }
}
```

> * References (Normalization) <br>
references store the relationships btw data by including links or references from one document to another. <br>
in general, this type makes your data compact, easy to store and easy to achieve consistency <--> low query performance and hard to scale horizontally


```javascript
// user document
{
    _id: <ObjectId1>,
    username: "Jinsoo"
}

// contact document
{
    _id: <ObjectId2>,
    user_id: <ObjectId1>,
    phone: "010-1234-4321",
    email: "hello.python89@wise-fashion.com"
}

// access document
{
    _id: <ObjectId3>,
    user_id: <ObjectId1>,
    level: 5,
    group: "dev"
}
```

## [MongoDB CRUD Operations](https://docs.mongodb.com/manual/crud/)

#### Insert Documents
> db.collection.insertOne() <br>
> db.collection.insertMany() <br>

```javascript
// if the collection does not currently exist, insert operation will create the collection
db.wisefashion.insertOne(
    {name: "Jinsoo", workspace: {floor: 5, team: "Dev"}}
)

// note that multiple documents in an array
db.wisefashion.insertMany([
    {name: "Choi", workspace: {floor: 5, team: "Oper"}}
    {name: "Jinsoo Choi", workspace: {floor: 4, team: "Dev"}}
])
```

#### Query Documents (Read)
> db.collection.find() <br>
> db.collection.find( field: value ) <br>
> db.collection.find( "field.nestedField": value ) <br>

```javascript
db.wisefashion.find({name: "jinsoo"})
db.wisefashion.find({"workspace.floor":5})
```

#### Update Documents
> db.collection.updateOne(filter, update, options) <br>
> db.collection.updateMany(filter, update, options) <br>
> db.collection.replaceOne(filter, update, options) <br>

```javascript
db.wisefashion.updateOne({name: "choi"}, {$set: {"workspace.floor": 3}})
db.wisefashion.updateMany({name: "choi"}, {$set: {"workspace.floor": 8}})
// replacing document does not need to follow the fields of the original document
db.wisefashion.replaceOne({name: "Choi"}, {name: "최", team: "Dev"})
```

#### Delete Documents
> db.collection.deleteOne(filter) <br>
> db.collection.deleteMany(filter) <br>

```javascript
db.wisefashion.deleteOne({name: "최"})
db.wisefashion.deleteMany({name: "choi"})
// deleting all documents from wisefashion collection without filter
db.wisefashion.deleteMany({})
```

## [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)





### references

1. https://www.youtube.com/watch?v=EE8ZTQxa0AM ('What is MongoDB' Eliot Horowitz, MongoDB's CTO)

2. https://www.youtube.com/watch?v=W3HhqMvwZP8 ('What is sharding?' from MongoDB)

3. http://egloos.zum.com/sweeper/v/3002591 ('EXEC와 동적쿼리' 수까락)

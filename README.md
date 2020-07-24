# Simple issue tracking service with Spring Boot 2 & Axon 4.x

Example implementation of CQRS pattern.

> The application uses an in-memory EventStore

Powered by:
* Spring Boot 2
* [Axon EventSourcing Framework](https://axoniq.io/)
* H2 database for view tables 

## Requirements

* Java 8 or newer

## WIP: Start the application

```bash
./mvnw clean spring-boot:run
```


## Web UI

open `http://localhost:8001` in your preferred browser.

## REST API

### Create an issue

```
POST http://localhost:8001/issues
Content-Type: application/json

{
  "title" : "Help!",
  "message": "Computer is broken"
}

---
# copy uuid that is returned by the service
0863571a-1350-4dba-b748-ac46955dccf1
```

### Follow the event stream ...

```
GET http://localhost:8001/issues/0863571a-1350-4dba-b748-ac46955dccf1/events
Accepts: application/json
```


### Check the current state of the issue ...
Queries the database using an Axon `@QueryHandler`
```
GET http://localhost:8001/issues
Accepts: application/json
```


### Assign an issue to a (known) user
> User management is not part of the example ... maybe later
```
PUT http://localhost:8001/issues/0863571a-1350-4dba-b748-ac46955dccf1/assignee
Content-Type: application/json

{
  "name": "Mark"
}
```

### Log work
```
PUT http://localhost:8001/issues/0863571a-1350-4dba-b748-ac46955dccf1/logwork
Content-Type: application/json

{
  "hours": 5.5
}
```

### Close the issue
```
PUT http://localhost:8001/issues/0863571a-1350-4dba-b748-ac46955dccf1/close
Accept: application/json
```
# webflux
Spring Boot demo project for Reactive/Webflux using Java 17

## Features
* Java 17
* Spring Boot 3.1 (May 2023)
* Reactive database code using R2DBC/Postgres
* Reative web framework ugin Spring WebFlux 
* Postgres Database
* Docker Compose for demo (non-test) database

## Testing
* Makes use of Clock to make testing time easier
* Unit tests use Mockito 
* Integration Tests use TestContainers for test database
* StepVerifier for testing reactive Mono<T>/Flux<T>
 
## TODO
* improve the testing of the once-per-second ticker
* add sample http requests/responses
* add support for adding new customers/modififying existing customers

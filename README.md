# Project Name

	Name: Spring boot with Elastic search
	Description: Simple REST api to load  a predefined data(In xml format) into an elastic search index and perform some basic search on the index.

## Feature

* API to to load  a predefined data(In xml format) from classpath into an elastic search index(/persons)
  ```
  	path: /api/person/load
  	HTTP verb: POST
  	
  	path: /api/person/search/{employeeId}
  	HTTP verb: GET
  ```
  
## Installation

* git clone https://github.com/arajitsamanta/spring-boot-elastic-search.git
* Import project into your favorite IDE
* Run "mvn clean install"

## Usage

* Please use any REST client test utilities like POSTMAN/Soap UI etc to test the service.

## History

* Version 1.0.0 Initial release

## Credits

* Arajit Samanta
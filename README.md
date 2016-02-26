# _Hair Salon_

#### _Store stylists and clients, February 26, 2016_

#### By _**Blair Peterson**_

## Description

_Create, view, update and delete stylist and client information_

## Setup/Installation Requirements

* _Clone this repository._
* _Make sure you have Java and Gradle installed._
    * _For Java:_
        * _Download and install [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)_
        * _Download and install [Java JRE](http://www.java.com/en/)_
    * _For Gradle: if you are using Homebrew on Mac:_
        * _$ brew update_
        * _$ brew install gradle_
* _Run the following command in terminal:_
  * _postgres_
* _Open a new tab in terminal and create the 'hair_salon' database:_
  * _psql_
  * _CREATE DATABASE hair_salon;_
  * _CREATE TABLE stylists(id serial PRIMARY KEY, name varchar);_
  * _CREATE TABLE clients(id serial PRIMARY KEY, name varchar, stylist_id int);_
  * _INSERT INTO stylists (name) VALUES ('**Unassigned**');_
* _In the top level of the cloned directory, run the following command in your terminal:_
    * _$ gradle run_
* _Open your web browser of choice to localhost:4567_

## Bugs

## Technologies Used

_Java, Spark, Junit, Velocity, Fluentlenium, Bootstrap_

### License

_This software is licensed under the MIT license._

Copyright (c) 2016 _**Blair Peterson**_

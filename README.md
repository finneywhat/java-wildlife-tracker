## Wildlife Tracker

###### Epicodus | Java Week Four Independent Project, 04.07.2017

##### By _**Chris Finney**_

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `_Clone the repository_`
* `In PSQL:`
  * `CREATE DATABASE wildlife_tracker;`
  * `\c wildlife_tracker;`
  * `CREATE TABLE animals (id serial PRIMARY KEY, name varchar);`
  * `CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);`
  * `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, endangered_animal_id int, time timestamp);`
  * `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`
* `_In the cloned repository root directory, run the command 'gradle run'_`
* `_Open browser and go to localhost:4567_`

### License

Copyright (c) 2017 **_MIT License_**

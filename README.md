# Hair Salon

#### Hair Salon, 03-31-2017

#### By **Stormi Hoebelheinrich**

## Description
Hair Salon is a system that allows a hair salon company to keep track of stylists and customers. It allows an administrator to add clients, stylists, and make updates and changes to data.

## Specifications

| Behavior                   | Input Example     | Output Example    |
| -------------------------- | -----------------:| -----------------:|
|Stylist instantiates with name and description| Stylist newStylist = new Stylist ("name", "description")|newStylist is an object of Stylist class|
|Client instantiates with stylistId, name and description| Client newClient = new Client (1, "name", "description")|newClient is an object of Client class|
|newStylist.save() saves stylist in database to stylists table and assigns id as Primary key|newStylist.save()|newStylist in database with id, true|
|newClient.save() saves client in database to clients table and assigns id as Primary key|newClient.save()|newClient in database with id, true|
|newStylist.delete() deletes stylist from stylists table|newStylist.deleteStylist()|newStylist no longer in database|
|newClient.deleteClient() deletes client from clients table|newClient.deleteClient()|newClient no longer in database|
|.all() returns list of Client or Stylist objects, depending on which it is called upon|Client.all()|List of all client objects|
|.find(int id) returns object with given id|Client.find(clientOne.getId())|clientOne|


## Setup/Installation Requirements

* Clone the repository
* In PSQL:
* CREATE DATABASE hair_salon;
* \c hair_salon;
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, description varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, stylist_id int, name varchar, description varchar);
* Run the command 'gradle run'
* Open browser and go to localhost:4567


### License

Copyright (c) 2017 **_Stormi Hoebelheinrich**

This software is licensed under the MIT license.

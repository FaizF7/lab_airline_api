# Lab - Airline API

In this exercise we will revisit the airline booking system, but this time we will do things slightly differently. Last time we built a CLI and managed the details of passengers and flights from the terminal but in the real world an airline wouldn't manage things this way. Instead they would have an API with routes available to make bookings through.

Obviously a real airline would have **much** more security in place around its API but for now we'll assume that the user is logged in and has permission to be interacting with ours. We will recreate all the functionality of the CLI project using Spring, with some extras which we couldn't previously include.

## MVP

Design and build an API with the same CRUD functionality as the CLI project. There will be a many-to-many relationship between a `passengers` table and a `flights` table. The `passengers` table will have columns for:

- `id`
- `name`
- either `phoneNumber` or `emailAddress`

The `flights` table will have columns for:

- `id`
- `destination`
- `capacity`
- `departureDate`
- `departureTime`

Your API must include routes enabling the user to:

- Add details of a new flight
- Display all available flights
- Display details of a specific flight
- Add a new passenger
- Display details of all passengers
- Display details of a specific passenger
- Book a passenger on to a flight
- Cancel a flight

A `DataLoader` class would be helpful to seed the database with some flights and passengers.

Ensure any operations affecting multiple tables are atomic by adding the `@Transactional` annotation where appropriate.

## Extensions

- Add functionality to filter flights by destination
- Prevent a passenger being booked on a flight which is full
- If you aren't already doing so, refactor the `departureDate` and `departureTime` properties to use one of the available datatypes for handling dates and times.
- Return `ResponseEntity` objects with appropriate status codes if the user attempts an invalid action, eg. return a `400` error if filtering by a destination which doesn't exist
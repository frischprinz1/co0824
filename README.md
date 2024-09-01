# Introduction

This barebones app lets a user select a tool to rent from a limited catalog, like a mini HomeDepot. This is not production-ready.

# Technologies used

-   Backend: Java, Spring Boot, Hibernate and MySQL
-   Frontend: Javascript, React, React Router, Styled Components and Radix UI component library
-   Testing: JUnit, Mockito, React Testing Library, Vitest
-   Other: Docker, Shell scripting

# Specification

-   Customers rent a tool for a specified number of days.
-   When a customer checks out a tool, a Rental Agreement is produced and displayed to the user with a few details like: the total number of chargeable days .

## Out of Scope

-   Users Account Management and Authentication
-   Timezone and Daylight savings
-   Logging and health monitoring

# Running the App

Requirements

-   Docker
-   curl

Open two terminal windows and navigate to the root directory of the project in each of them.

1.  Run setup.sh. This sets up the database in docker, builds the Java project using gradle and executes the resulting .jar file. Test this by running:

    curl http://localhost:8080

    You should see a welcome message if everything went well.

2.  Run client.sh after the first step is done to build the React client side app. Now type this in your browser and you should see the app:

    http://localhost:5173

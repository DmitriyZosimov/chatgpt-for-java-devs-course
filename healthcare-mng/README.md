# Healthcare Management System

[![Backend build](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/actions/workflows/build.yaml/badge.svg)](https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/actions/workflows/build.yaml)
<svg fill="none" viewBox="0 0 120 120" width="120" height="120" xmlns="http://www.w3.org/2000/svg">
    <foreignObject width="100%" height="100%">
        <div xmlns="http://www.w3.org/1999/xhtml">
            <img alt="Coverage" src="https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course/blob/master/.github/badges/task3-sr-coverage.svg"/>
        </div>
    </foreignObject>
</svg>

The Healthcare Management System is a Spring Boot application that allows healthcare providers to manage patient records, 
schedule appointments, prescribe medications, and provide directions to healthcare facilities using Google Maps integration.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Google Maps Integration](#google-maps-integration)
- [Testing](#testing)

## Features

- **Patient Management**: Create, update, and delete patient records. Associate patients with prescribed medications.

- **Appointment Scheduling**: Create, update, and delete appointments for patients.

- **Medication Management**: Manage a list of available medications.

- **Google Maps Integration**: Get directions to healthcare facilities using Google Maps API.

## Prerequisites

Before you start, ensure you have the following prerequisites:

- Java 8 or later installed.
- Maven for building the project.
- [Google Maps API Key](https://developers.google.com/maps/gmp-get-started) for directions integration.

## Getting Started

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/DmitriyZosimov/chatgpt-for-java-devs-course.git
   ```
2. Navigate to the project directory:

    ```bash
    cd healthcare-mng
    ```
   
3. Update a src/main/resources/application.properties file where add your Google Maps API Key:
    
   ```
    google.maps.api.key=YOUR_GOOGLE_MAPS_API_KEY
    ```
   
4. Build the project using Maven:
   ```bash
   mvn clean install
    ```
   
5. Start the application:
    ```bash
   java -jar target/healthcare-mng-1.0-SNAPSHOT.jar
   ```
6. The application will start on http://localhost:8080.

## API Endpoints

**Patients:**

* **GET** /patients: Get a list of all patients.
* **GET** /patients/{id}: Get a patient by ID.
* **POST** /patients: Create a new patient.
* **PUT** /patients: Update an existing patient.
* **DELETE** /patients/{id}: Delete a patient.
* **POST** /{patientId}/medications/{medicationId}: Add a medication to a patient

**Appointments:**

* **GET** /appointments: Get a list of all appointments.
* **GET** /appointments/{id}: Get an appointment by ID.
* **POST** /appointments: Create a new appointment.
* **PUT** /appointments: Update an existing appointment.
* **DELETE** /appointments/{id}: Delete an appointment.

**Medications:**

* **GET** /medications: Get a list of all medications.
* **GET** /medications/{id}: Get a medication by ID.
* **POST** /medications: Create a new medication.
* **PUT** /medications: Update an existing medication.
* **DELETE** /medications/{id}: Delete a medication.

**Directions**:

* **GET** /directions: Get directions from Google Maps. Requires origin and destination query parameters.

## Google Maps Integration
The application integrates with Google Maps to provide directions to healthcare facilities. To use this feature, make sure you have configured your Google Maps API Key in application.properties as mentioned in the Prerequisites section.

## Feedback
**1. Was it easy to complete the task using AI?**

Yes, ChatGPT helps quickly complete a lot of dreary job.

**2. How long did task take you to complete?**

I spent about 3-4 hours to complete this tasks.

**3. Was the code ready to run after generation? What did you have to change to make it usable?**

I added some methods and made a little cosmetic work.

**4. Which challenges did you face during completion of the task?**

If you update some part of a code, you will need to correct the following suggested answer regarding your changes.



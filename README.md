# Notes Management API

## Features
- User CRUD
- Note CRUD
- User-Note Relationship
- Pagination
- Sorting
- Search by Title
- Validation
- Global Exception Handling

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

## API Endpoints

### Users

| Method | Endpoint |
|---------|----------|
| POST | /users |
| GET | /users |
| GET | /users/{id} |
| PUT | /users/{id} |
| DELETE | /users/{id} |
| GET | /users/{id}/notes |

### Notes

| Method | Endpoint |
|---------|----------|
| POST | /notes |
| GET | /notes |
| GET | /notes/{id} |
| PUT | /notes/{id} |
| DELETE | /notes/{id} |
<<<<<<< HEAD
# Topic #24: Charity Donation Tracker API

## Project Description
An internal ledger API designed for non-profit fundraising operations. This application tracks unique donors, targets fundraising campaigns, and processes financial donations while keeping automated audit histories.

## Technical Specifications & Configuration
- **Backend framework**: Spring Boot 3.2.5
- **Build tool**: Maven
- **Database Engine**: H2 In-Memory Database (Console: `/h2-console` | Credentials: user `sa`, pass `password`)
- **Interactive API Documentation**: Swagger UI available at `/swagger-ui/index.html`

## Strategic Business Invariant
**Enforcement Rule**: All processing donation amounts must be strictly positive. 
If any request submits an amount equal to or less than zero ($0.00$), the endpoint immediately halts execution and handles the issue by generating a uniform validation response (`400 Bad Request`). Furthermore, donations processed against inactive (closed) campaigns will explicitly reject with a `409 Conflict`.

## Security Enforcement
Every secured endpoint requires a fixed header authorization credential:
- **Header Key**: `X-API-Key`
- **Header Value**: `demo-key-2026`

## Execution Guidelines
Run the application using your terminal execution command:
```bash
mvn spring-boot:run
=======
# Charity-Donation-Tracker
>>>>>>> b381680cbc8188ac52292866b2a03356ca642b99

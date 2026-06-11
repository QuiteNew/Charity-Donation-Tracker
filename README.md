# Charity Donation Tracker API (UACS 2026)

## Project Description
This non-profit donation tracking system was developed as part of the [Computer Interface Programming](https://github.com/QuiteNew/Fullstack-Flux-The-Rapid-Developer-Education-Program/tree/main/Computer%20Interface%20Programming) class. This system will be used as a focal point where donors contribute their monies for specific drives so that the organization can keep track of how much money they have raised in total, issue receipt simulations for any monetary transactions, and also know the progress of every drive towards achieving its target amount.

## How to Run Locally
Run the application using your terminal execution command:
```bash
./mvnw spring-boot:run

```

## 1. Register a Donor (Create)

```bash
curl -X POST http://localhost:8080/api/donors \
-H "Content-Type: application/json" \
-H "X-API-Key: demo-key-2026" \
-d '{"name": "Jane Doe", "email": "jane@example.com"}'
```

### 2. Retrieve Campaign Details (Read)

```bash
curl -X GET http://localhost:8080/api/campaigns/1 \
-H "X-API-Key: demo-key-2026"
```

### 3. Close a Campaign (Update)
```bash
curl -X PATCH http://localhost:8080/api/campaigns/1/close \
-H "X-API-Key: demo-key-2026"
```
### 4. Soft Delete a Campaign (Delete)
```bash
curl -X DELETE http://localhost:8080/api/campaigns/1 \
-H "X-API-Key: demo-key-2026"
```

### 5. View Campaign Progress (Report)
```bash 
curl -X GET http://localhost:8080/api/reports/campaign-progress \
-H "X-API-Key: demo-key-2026"
```


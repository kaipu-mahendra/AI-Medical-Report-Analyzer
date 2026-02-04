# Medical Report Analyzer Backend

This is the Spring Boot backend for the AI-Powered Medical Report Analyzer.

## Prerequisites

1. **Java 17** or higher
2. **Maven** (optional if using IDE)
3. **Tesseract OCR Data**:
   - Download the `eng.traineddata` file from [tessdata repository](https://github.com/tesseract-ocr/tessdata).
   - Create a folder named `tessdata` in the `backend` directory (or project root).
   - Place `eng.traineddata` inside `tessdata/`.

## Setup & Run

1. **Database**:
   - By default, the application uses **H2 In-Memory Database**.
   - You can access the H2 Console at `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:medicaldb`
   - User: `sa`
   - Password: `password`
   - To use MySQL, update `src/main/resources/application.properties`.

2. **Run the Application**:
   - Use your IDE to run `MedicalReportAnalyzerApplication.java`.
   - Or via command line: `mvn spring-boot:run`

## API Endpoints

- **Upload Report**: `POST /api/reports/upload` (param: `file`, `patientName`)
- **Get Analysis**: `GET /api/reports/{id}/analysis`

## Structure

- `controller`: REST Endpoints
- `service`: Business logic (OCR, Analysis, Explanation)
- `model`: JPA Entities
- `repository`: Data Access
- `util`: Helpers (DataLoader)

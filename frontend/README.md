# Medical Report Analyzer Frontend

This is the React frontend for the AI-Powered Medical Report Analyzer.

## Prerequisites

- **Node.js** (v16 or higher)
- **npm**

## Setup & Run

1.  **Install Dependencies**:
    ```bash
    cd frontend
    npm install
    ```

2.  **Run Development Server**:
    ```bash
    npm run dev
    ```
    The app will be available at `http://localhost:5173`.

## Features

- **File Upload**: Upload PDF or Image medical reports.
- **Analysis View**: View extracted test results, status (Normal/High/Low), and explanations.
- **Responsive Design**: Built with Tailwind CSS.

## Configuration

- The API URL is currently hardcoded to `http://localhost:8080/api/reports/upload` in `src/components/FileUpload.jsx`.
- Ensure the Spring Boot backend is running on port 8080.

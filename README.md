# API Automation Framework

This repository contains an API automation framework for testing user-related endpoints.

## Project Structure

The project is structured as follows, based on your provided preview:

api-automation-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/uma/api/
│   │   │       ├── dto/
│   │   │       │   └── UserDto.java          # Data Transfer Object for User
│   │   │       ├── endpoints/
│   │   │       │   └── UserEndpoints.java    # API endpoint definitions
│   │   │       ├── models/
│   │   │       │   └── User.java             # Model class for User
│   │   │       ├── services/
│   │   │       │   └── UserService.java        # Service class for API interactions
│   │   │       ├── utils/
│   │   │       │   ├── ConfigurationManager.java # Configuration handling
│   │   │       │   ├── CustomAssertions.java    # Custom assertions
│   │   │       │   ├── DatabaseSetupManager.java # Database setup
│   │   │       │   ├── DataGenerator.java       # Data generation utilities
│   │   │       │   ├── ExcelReader.java       # Excel data reading utilities
│   │   │       │   ├── JsonUtils.java         # JSON utilities
│   │   │       │   ├── ReportManager.java     # Report generation utilities
│   │   │       │   └── RequestSpecBuilderUtil.java # Request specification builder
│   │   │   ├── resources/
│   │   │   │   ├── JsonSchema/             # JSON schema files
│   │   │   │   ├── Configuration.properties # Configuration properties
│   │   │   │   └── extent-config.xml       # Extent report configuration
│   │   └── test/
│   │       ├── java/
│   │       │   └── org/uma/api/validations/
│   │       │       ├── UserValidations.java         # TestNG validations
│   │       │       └── UserValidationsViaExcel.java # TestNG validations using Excel data
│   │       ├── resources/
│   │       │   └── ...                     # Test resources (if any)
│   ├── target/                         # Build output
├── pom.xml                             # Maven project configuration
└── README.md                            # This file




## Technologies Used

* **Java:** Programming language.
* **TestNG:** Testing framework.
* **Rest Assured:** API testing library.
* **Maven:** Build automation tool.
* **ExcelReader:** For reading data from Excel files.
* **Extent Reports:** For generating HTML reports.

## Setup

1.  **Clone the Repository:**

    ```bash
    git clone <repository_url>
    ```

2.  **Navigate to the Project Directory:**

    ```bash
    cd api-automation-framework
    ```

3.  **Build the Project using Maven:**

    ```bash
    mvn clean install
    ```

4.  **Configure `Configuration.properties`:**

    * Update the `Configuration.properties` file in `src/main/resources/` with your API's base URL and any other necessary configurations.

5.  **Run the Tests:**

    * To run all tests:

        ```bash
        mvn test
        ```

    * To run specific test classes or methods, you can use the TestNG plugin in your IDE or configure Maven to run specific tests.

## Test Classes

* **`UserValidations.java`:** Contains TestNG tests for validating user-related API endpoints.
* **`UserValidationsViaExcel.java`:** Contains TestNG tests that use data from an Excel file to validate user-related API endpoints.

## Utilities

* **`UserService.java`:** Provides methods for interacting with user-related API endpoints.
* **`UserDto.java`:** Data Transfer Object (DTO) for user data.
* **`User.java`:** Model class representing the user entity.
* **`ExcelReader.java`:** Utility class for reading data from Excel files.
* **`CustomAssertions.java`:** Provides custom assertions for API testing.
* **`RequestSpecBuilderUtil.java`:** Utility class for building request specifications.
* **`ConfigurationManager.java`:** Utility class to handle the property files.
* **`DataGenerator.java`:** Utility class for generating test data.
* **`JsonUtils.java`:** Utility class for handling json related tasks.
* **`ReportManager.java`:** Utility class for managing reports.

## Reports

* Test reports are generated using Extent Reports.
* The `extent-config.xml` file in `src/main/resources/` is used to configure the reports.
* Reports are generated in the `target/surefire-reports/` directory after running the tests.

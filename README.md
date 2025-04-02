# API Automation Framework

This repository contains an API automation framework for testing user-related endpoints.




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

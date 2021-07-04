# Aequilibrium Tech Assignment
Aequilibrium - QA Techincal Assignment


The project contains 2 exercises for the techincal assignment.

## Exercise 1 : Manual Test Cases for the Login Feature.
Manual Test cases file is stored at : [Exercise 1 : Manual Test cases file](https://github.com/jainanant4567qa/AequilibriumTechAssignment/blob/master/Exercise%201%20-%20Manual%20Test%20cases%20for%20Login%20Feature.txt)

## Exercise 2 : Automation test cases for selected testcases from the Manual Test cases.

### Concept implement for the Framework Design :

- [x] TestNg as Base Framework for test case design.
- [x] Dependency maintainance using the Maven tool.
- [x] Using Test Data from an external location using JSON and Configuration files.
- [x] Implemented Page Object Model and Page Factory to design the Test suite.
- [x] Implemented log4j and Extent Report for logs and Reporting.
- [x] Implemented externalised test configuration.
- [x] Implemented screenshot capture for all the pass and failed test cases.
- [x] Custom Ulitily class for implementing Common web page interaction methods.
- [x] Supports Multiple Browser execution eg : Chrome and Firefox.

### Execution
1. Run the suite using the TestNg.xml file
2. Run the suite using the POM.xml
3. Run the suite from command prompt, navigate to **AequilibriumTechAssignment >** directory and run: `mvn clean install`.
4. Note : To use different browser for execution update the value field under **testNg.xml** file > ` <parameter name="browser" value="chrome"></parameter>`as _chrome_ or _firefox_

### Reporting
Reports after each test run are written into the /Reports directory in the project structure.

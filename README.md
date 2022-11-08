### Rest Assured Framework with Cucumber

BDD framework for automation using Rest Assured Cucumber and Junit

The framework has the following features

1. Modular Design with Page Object Modal pattern.
2. Maven based folder structure.
3. Log4j enabled for logging
4. Extent reporting
5. Configurable property file for static content
6. Tagging of each test case to run selected one by mentioning it in TestRunner tag section.
7. Each functionality has its own feature file which is easy to read and understand.
8. Under each feature we still can select test cases we want to run by using tags
9. Folder structure has been added for future support of request payload and response payload. 

### To Execute the Suite
1. Build the project using mvn lifecycle.
2. Run the TestRunner.java, by default it contains presence of all tags.
3. Suite is configurable to increase or decrease the number of test cases required for execution.
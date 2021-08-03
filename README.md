# stg-java-webdriver-cert

My implementation of the 7 test challenges for the STG Java/WebDriver Level 1 (Automation Associate) certification.

It is built with [Apache Maven](https://maven.apache.org/), using 4 dependencies:
- selenium-chrome-driver
- selenium-support: Includes Selenium ExpectedConditions, Select, and WebDriverWait classes
- testng: The [TestNG](https://testng.org/doc/) Java testing framework
- webdrivermanager: [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) is a library to automate the management of the drivers (e.g., chromedriver, geckodriver, etc.) required by Selenium WebDriver.

[For more information about these dependencies, see pom.xml.]

## Implementation Notes

1. This code was developed and tested using:
- OpenJDK 11 LTS (11.0.11+9)
- Apache Maven 3 (3.6.3)
- Google Chrome (v92.0.4515.107) in Windows (Windows 8.1 Professional)
- IntelliJ IDEA 2021.2 (Community Edition)

2 This code uses [webdriver_manager](https://github.com/bonigarcia/webdrivermanager) to simplify managing WebDriver instances for different browsers.
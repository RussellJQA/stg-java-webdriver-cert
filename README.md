# stg-java-webdriver-cert

My implementation of the 7 test challenges for the STG Java/WebDriver Level 1 (Automation Associate) certification.

It has 6 dependencies:

- selenium-chrome-driver
- selenium-edge-driver
- selenium-firefox-driver
- selenium-support: Includes Selenium ExpectedConditions, Select, and WebDriverWait classes
- testng: The [TestNG](https://testng.org/doc/) Java testing framework
- webdrivermanager: [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) is a library to automate the
  management of the drivers (e.g., chromedriver, edgedriver, geckodriver [for Mozilla Firefox], etc.) required by
  Selenium WebDriver.

These dependencies can be installed with [Apache Maven](https://maven.apache.org/), using the repository's pom.xml
Project Object Model file.

Strictly speaking:

- selenium-chrome-driver: is needed only to run the 7 test challenges in Google Chrome, using
  src\test\challenges\testChromeUSAll.xml
    - This runs the 7 test challenges using the US Websites:
        - [Google US](https://www.google.com/)
        - [Copart US](https://www.copart.com/)
- selenium-edge-driver: is needed only to run the 7 test challenges in Microsoft Edge, using
  src\test\challenges\testEdgeCanadaAll.xml
    - This runs the 7 test challenges using the Canadian Websites:
        - [Google Canada](https://www.google.ca/)
        - [Copart Canada](https://www.copart.ca/)
- selenium-firefox-driver: is needed only to run the 7 test challenges in Mozilla Firefox, using
  src\test\challenges\testFirefoxUKAll.xml
    - This runs the 7 test challenges using the UK Websites:
        - [Google UK](https://www.google.co.uk/)
        - [Copart UK](https://www.copart.co.uk/)

## Implementation Notes

1. This code was developed and tested using:

- OpenJDK 11 LTS (11.0.11+9)
- TestNG 7.4.0
- Apache Maven 3 (3.6.3)
- IntelliJ IDEA 2021.2.1 (Community Edition)
- The following browsers in Windows (Windows 8.1 Professional)
    - Google Chrome 93.0.4577.63
    - Microsoft Edge 93.0.961.38
    - Mozilla Firefox 91.0.2

2. It uses [webdriver_manager](https://github.com/bonigarcia/webdrivermanager) to simplify managing WebDriver instances
   for different browsers.
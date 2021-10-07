# stg-java-webdriver-cert

My implementation of the 7 test challenges for the STG Java/WebDriver Level 1 (Automation Associate) certification:

- Challenge 1: Uses Selenium WebDriver to perform some simple tests on a Google homepage (e.g., https://www.google.com/)
- Challenges 2, 3, 5, 6, and 7: Use Selenium WebDriver to perform some tests on a Copart homepage (
  e.g., https://www.copart.com)
- Challenge 4: Recursively computes
  the [Fibonacci numbers for n=0 through n=300](http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibtable.html#100)
  , and outputs the results as both numbers and words. For example, for n=300:
    - the resulting number is 222,232,244,629,420,445,529,739,893,461,909,967,206,666,939,096,499,764,990,979,600
    - the resulting words are "two hundred twenty two novemdecillion, two hundred thirty two octodecillion, two hundred
      forty four septdecillion, six hundred twenty nine sexdecillion, four hundred twenty quindecillion, four hundred
      forty five quattuordecillion, five hundred twenty nine tredecillion, seven hundred thirty nine duodecillion, eight
      hundred ninety three undecillion, four hundred sixty one decillion, nine hundred nine nonillion, nine hundred
      sixty seven octillion, two hundred six septillion, six hundred sixty six sextillion, nine hundred thirty nine
      quintillion, ninety six quadrillion, four hundred ninety nine trillion, seven hundred sixty four billion, nine
      hundred ninety million, nine hundred seventy nine thousand, six hundred"

Although it is quite natural to calculate Fibonacci numbers recursively, this can be quite computationally expensive in
programming languages (such as Java and Python) which don't automatically
eliminate [tail recursion](https://www.softwaretestinghelp.com/recursion-in-java/#1_Tail_Recursion). However, this is
avoided by [memoization](https://en.wikipedia.org/wiki/Memoization): by caching previously computed Fibonacci numbers,
so they don't need to be re-computed.

The implementation code has 7 dependencies:

- [dotenv-java:](https://github.com/cdimascio/dotenv-java) reads key-value pairs from a .env file and can set them as
  environment variables. It helps in the development of applications following the [12-factor](https://12factor.net/)
  principles. [Factor 3 is "Store configuration in the environment."]
- selenium-chrome-driver
- selenium-edge-driver
- selenium-firefox-driver
- selenium-support: Includes Selenium ExpectedConditions, Select, and WebDriverWait classes
- [testng:](https://testng.org/doc/) a Java testing framework
- [webdrivermanager:](https://github.com/bonigarcia/webdrivermanager) a library to automate the management of the
  browser drivers (e.g., chromedriver, edgedriver, geckodriver [for Mozilla Firefox], etc.) required by Selenium
  WebDriver.

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
- Apache Maven 3 (3.6.3)
- IntelliJ IDEA 2021.2.1 (Community Edition)
- dotenv-java 2.2.0
- TestNG 7.4.0
- Selenium 4.0 RC2
- The following browsers in Windows (Windows 8.1 Professional)
    - Google Chrome 94.0.4606.71
    - Microsoft Edge 94.0.992.38
    - Mozilla Firefox 93.0
- webdrivermanager 5.0.3

2. It uses [webdriver_manager](https://github.com/bonigarcia/webdrivermanager) to simplify managing WebDriver instances
   for different browsers.
# seleniumjasperreport
Simple example of how to use selenium to create front end test and save the evidence into jasper report.
It is not necessary to use external api. You can create your own code following this simple example.

The class "SeleniumJUnit.java" starts the JUnit test to create the pdf evidence.

Environment used to create this project:
- Eclipse Luna
- Maven 3.3.9
- The project uses JDK 1.8.0_111
- IReport 5.6.0
- JDK 1.7.0 (IReport just works on JDK7. Alter the file "iReport-5.6.0/etc/ireport.conf" to set the JDK 7).
- Browser driver downloaded manually from Selenium website (http://www.seleniumhq.org/download/). This project uses "chromedriver.exe".

# Galaxy-Merchant-Trading-Space

- This project using Maven as build environment and its dependency.
- The entry point of this project located in org.propspace.main.Main.
- The main logic process of this project is in org.propspace.trading.service.ProjectTradingService.
- to build this project as executable jar ,use command 'mvn clean package' on the root project directory.

The Design
this application using org.propspace.trading.service.ProjectTradingService with a constructor which take java.io.InputStream as parameter.
the method proceedData takes java.io.OuputStream as parameter to write the result of processed data from input.
the data from InputStream which is passed at constructor will be processed per line break separator, so using default InputStream from 
System.in is Acceptable and can work correctly.
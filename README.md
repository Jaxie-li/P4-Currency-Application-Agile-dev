# SOFT2412_ASM1_CC14_G5

### For admin rights, when you want to enter the admin page to perform some operations.
#### Please use:
 **Username:admin Password:123**  
 **Username:admin1 Password:666**
 
## How to test
The ```gradle build``` ```gradle test``` commands are used in the terminal to execute these tests.
When the tests all pass, you will see "BUILD SUCCESSFUL" in the terminal.  
enter ```gradle build clean``` then enter the command ```gradle test jacocoTestReport``` to generate reports for both test coverage and code coverage in the reports.  

There are two ways to visit jacoco Test Reports:  
1, Open **build/reports/jacoco/html/index.html**   
2, Open jacoco test reports in Jenkins, please visit "http://soft2412.work:8080/"  
->username:admin  
->password:2412hdplz

Then you can see JaCoCo Coverage Report now. No matter which way you visit jacoco test reports that's all details you can see for the coverage rates.



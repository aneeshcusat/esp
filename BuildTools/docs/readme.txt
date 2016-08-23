Initial Setup
-------------
Create the schema and change log table -- > Files are located in BuildTools/scipts

Groovy plugin >> Install groovy eclipse plugin from (http://dist.springsource.org/snapshot/GRECLIPSE/e4.5/) optional if you need to run test cases from eclipse and ignore groovy compile errors

Build
-----
goto the project root folder and run below the command, which will automatically configure gradle.
>gradlew.bat\gradlew clean build war tomcatRun

OR

if gradle is installed and configured run below the command.
>gradle clean build war tomcatRun 

Eclipse configure
---------------
>gradle eclipse or gradlew eclipse

Test
----
open browser and hit http://localhost:8080 for the home pages
http://localhost:8080/rest/* for rest services
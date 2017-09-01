# JSF_Intro
CSC 470 Software Engineering // Basic project intro to Java Server Faces

## Assignment Overview:
You will download a zipfile of this project with the basic framework already in place. The project file is a simple game that
allows users to guess a number and see if it matches the correct guess. For this project you will follow the 
modification directions in the assignment handout to add to the .xhtml pages and to auto-generate a Managed Bean and add the 
correct annotations and imports. 

You will also add the following functionality for users to view statisical information. 

* 1) Add a "View Statistics" button to the index.xhtml
https://github.com/mikecrbenton/JSF_Intro/blob/master/src/main/webapp/index.xhtml ( Line 24 )

* 2) Add functionality to the UserNumberBean class to keep track of statistics for the session
https://github.com/mikecrbenton/JSF_Intro/blob/master/src/main/java/guessnumber/UserNumberBean.java
  * a) For each number guess (0-9) how many times was that number generated ( Line 97, Line 172 )
  * b) What are the average number of times it takes to guess a number ( Line 191 )
  * c) What are the total number of games played ( Line 203 )
  
* 3) Create a "statistics.xhtml" page that displays your statistics (Use a h:dataTable to display the number of 
  times each number is generated)
https://github.com/mikecrbenton/JSF_Intro/blob/master/src/main/webapp/statistics.xhtml

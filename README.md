
#Read Me

##GPC Power Consumption Monitoring and Control System

###Ontario Engineering Competition 2017 - Programming

###January 27, 2017

####Team: Carleton University 1

Team Members:

Charles Bergeron

Christopher Briglio

David Briglio

Daniel Sauvé


If you are looking for the user manual explaining how to install and operate the program, please refer to "User Manual.pdf".

--Program Description--

The GPC Power Monitoring and Control System is a program designed to autonomously route and monitor power distribution for all of GPC's power grid. The system was built to handle power outages within the grid by dynamically calculating the shortest path from all houses to available power sources, and the associated costs. It is also focused on minimizing costs by taking into account power consumption and transmission costs when calculating power distribution paths. The program was designed to be a simple, user friendly experience providing all relevant, up-to-date information at the click of a button.


--Project Setup--

Take the program folder and place it on your computer. Once you have all the necessary files on the computer you may run the programs using the .exe file included in the main directory.


--External Libraries--

Maven

Website: https://maven.apache.org/

All other external dependencies are managed by Maven. Maven is a software project management tool used to manage project builds, dependencies and documentation.

JUnit

Website: http://junit.org/junit4/

Used for some unit testing for the path planning portion of this program
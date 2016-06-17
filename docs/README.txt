


			Name : Kumar Sasmit (110308698)
			Homework 1: All about DNS
			CSE 534, Spring 2016
			Instructor: Aruna Balasubramanian



External Libraries used:
--------------------------

 I used java package by dnsjava. These library is included by importing org.xbill.DNS package which are included in org.xbill.dns_2.1.7.jar , which can be downloaded from http://www.dnsjava.org/

High level view of the design.
--------------------------------

The goal of this homework is to make an iterative DNS resolver and a dig tool.
The mydnsresolver and mydig tool uses dnsjava APIs to do DNS resolution for the URL address passed as input.
The resolution in done in a series of steps.
A root list of all the root DNS servers has been hardcoded. For each input, the first query is made to the first root server which sends as response the top level domain server details serving the domain name of the URL. The obtained server  details are saved in another list and the next request is made iteratively to the top level DNS server from the list, Upon each response the list is cleared and filled with the details of the servers at the next level. On any failure in fetching the response the next list server is tried. Similarly if the root server fails to respond, the next root level DNS is tried, picking the server from the root list in a round-robin fashion. If none of the servers are able to resolve the query, an error message is generated.
mydnsresolver takes a single command line argument, which is the url of the website to be resolved, and prints out the IP address.
mydig takes two arguments the name(URL) and the type(A,MX,NS) and prints out the response similar to that done by dig tool.

How to run the programs:
--------------------------

I used JAVA for implementing mydnsresolver and mydig as per the requirements in Part A and Part B of the assignment.

Requirements: The system should have JDK installed and system path variables set for java and javac.


The java source files can be compiled and executed by the following steps:
1> put all the files in a single directory.
2>On the command promp go to that directory.
3>execute the following commands
javac -cp <jarfile.jar> <javafile.java>		//generates javafile.class file
java -cp .;<jarfile.jar> <javafile> <args[0]> <args[1]>


Example

javac -cp org.xbill.dns_2.1.7.jar mydnsresolver.java
java -cp .;org.xbill.dns_2.1.7.jar mydnsresolver www.google.com

javac -cp org.xbill.dns_2.1.7.jar mydig.java
java -cp .;org.xbill.dns_2.1.7.jar mydig www.google.com A


Included files' details:
-------------------------
1. mydig_output.txt includes the expected output for running mydig program for the 3 types (A, MX, NS) along with the input.
2. Part_C&D.docx is a MS-word document with the solution to Part-C and Part-D. The CDF for part C has been drawn using MS-excel and pasted to this doc.
3. test.java is the test code written to execute the 25 top web sites, 10 times each and to calculate the average time and write the output on a text file. Also googleresolver.java, localresolver.java have been included to check the performance of google DNS resolver and the local DNS resolver. The test script in test.java can be modified to execute the same test cases for google and local DNS by just changing the class name where the main function call has been made. <line no. 48> in test.java.
4. The jar file needed for compilation too has been included. "org.xbill.dns_2.1.7.jar"
#Install & Run the application:
1. Copy the assignment5 folder and paste it into the webapps folder of tomcat directory.
2. All the servlets are mentioned in the deployment descriptor file web.xml
3. Then start the MySql server. After both the database servers are started,
then start the Tomcat server.
4. Run the application by writing localhost/assignment5 in the browser.




------------------

#Store manager portal with the link to Inventory and sales report 
 has been provided in the left navigation bar

#All the code for MySQL is in the class MySqlDataStoreUtilities

------------------
To compile the folder navigate to the folder in webapps and type the following code.

javac src/* -classpath "C:\apache-tomcat-7.0.34\lib\servlet-api.jar;"C:\apache-tomcat-7.0.34\lib\json.jar";
"C:\apache-tomcat-7.0.34\lib\mysql-connector-java-5.1.40-bin" -d WEB-INF/classes




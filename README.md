# Servlet-JDBC
Code for connecting, displaying and inserting information into/from a MySQL database.

When connecting the JDBC to the servlet be sure that the follow things have been done:
	1. The latest JDBC driver is downloaded and is places into the current project.
		Right click on the current project -> Click on build path -> Goto libraries -> Add JAR
	2. Make sure the driver is placed within the lib folder as well.
		Copy the driver into the lib folder.
	
	NOTE:
	Best practice is to use prepared statements for the sql cmds.

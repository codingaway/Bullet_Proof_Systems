We are using form-based authentication using JDBC realm. Required Users, Groups are created by app on depoyment but GlasshFish server needs to be configured to allow this authentication mechanism properly.

GlassFish Configuration
=============================
Please configure GlassFish server as follows to enable form-based login::
	
	- In NetBeans switch to the "Services" tab  and right click on the "GlassFish 4+" node. 
	
	- Select "Start" and wait for GlassFish server to fully start. 
	
	- Right click again and select "View Domain Admin Console", (This should open default browser pointing you to http://localhost:4848/) 
	
	- Configurations > server-config > Select on "Security"
		Check "Default Principal To Role Mapping" to "Enable" then Click "Save"

	- Select "Configurations > server-config > Security > Realms" and click "New..." . 
	
	- Enter a name JDBCRealm and select the com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm from the drop down. Fill in the 
	following values into the textfields:

JAAS 							=	jdbcRealm
JNDI 							=	jdbc/sample
User Table 						=	GP14USER
User Name Column 				=	USERNAME
Password Column 				=	PASSWORD
Group Table	 					= 	GP14GROUP
Group Table User Name Column	=	USERNAME
Group Name Column				= 	GROUPNAME
Password Encryption Algorithm 	= 	none
Digest Algorithm:				= 	none

Leave all the other defaults/blanks and select "SAVE" in the upper right corner.


Alternativly included "domain.xml" file can be used to configure GlassFish server(may require some machine dependent modification) 
	- Backup existing domain file from {Netbeans Dir}//glassfish-4.1.1/glassfish/domains/domain1/config/ 
	- Copy included "domain.xml" and restart the server.

Refrerences:
------------
	1. Source: http://blog.eisele.net/2013/01/jdbc-realm-glassfish312-primefaces342.html
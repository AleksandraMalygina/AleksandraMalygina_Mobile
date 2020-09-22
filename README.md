# AleksandraMalygina_Mobile
In order to test native application, the file with application under test ("EPAMTestApp.apk") should be added to the folder ./src/main/resources
There are two profiles provided in the project:
 - "native" - to run native application tests 
 - "web" - to run web application tests
 To run project with specific profile use command: "mvn -P<profileName> test" where <profileName> is the name of the profile you want to use

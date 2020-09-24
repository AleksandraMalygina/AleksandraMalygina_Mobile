# AleksandraMalygina_Mobile
In order to test native application, the file with application under test ("EPAMTestApp.apk" for Android and "EPAMTestApp.ipa" for iOS) should be added to the folder "./src/main/resources".

There are several profiles provided in the project with the following identifiers:
 - "native" - to run native application tests 
 - "web" - to run web application tests
 - "cloud" - to run tests on remote cloud service
 - "iOS" and "Android" refer to the target platform
 
 To run project with specific profile use command: "mvn -P\<profileName\> test" where \<profileName\> is the name of the profile you want to use.

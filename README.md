# Balu


==> Maven installation
-------------------------
# java --version

Now, refer maven in 5 minute of installation
# wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
# sudo tar -xvzf apache-maven-3.9.6-bin.tar.gz
# sudo mv apache-maven-3.9.6 /opt/
# cd /opt/apache-maven/bin/mvn

Now add the mvn command in enviorment
# sudo vim /etc/profile
  export PATH=$PATH:'/opt/apache-maven-3.9.6/bin/'
# source /etc/profile
# bash
# mvn --version
=======================================================================================================

Maven command Syntax : mvn goal argument
                       aws sub-command  extra parameter
=======================================================================================================

==> Create a Maven project
------------------------------

Syntax: mvn archetype:generate
        command (libreary):(The goal need to download from liabrary)

mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

# mvn archetype:generate -DgroupId=com.studentapp-ui.app -DartifactId=studentapp-ui-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
# cd studentapp-ui-app
# tree

Note: Developer decide the build tool, here directory structre is created for java based application from here we started the Applicaiton development lifecycle

1. First directory has define
2. Then we write the application/code
eg. we can check studentapp also

## our-project has created
==============================================================================================================================================================================================================

Maven Lifecycle:
----------------
Basically it a process collection of maven phases

Mven lifecycle Type:
1. Default - by default behavior of lifecycle
2. Clean - It discard old build
3. Site - It helps of documentation
-------------------------------------------------------

Maven Phases: --> Collection of goals/libreary
Default Phases
------------------------------------------
1. validate: validate the project is correct and all necessary information is available
2. compile: compile the source code of the project (Syntax checks, Human redable lamgaue convert m/c readable)
3. test: test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed (test)
4. package: take the compiled code and package it in its distributable format, such as a JAR. (Pkg created)
5. integration-test: process and deploy the package if necessary into an environment where integration tests can be run (integration of enviorment)
6. verify: run any checks to verify the package is valid and meets quality criteria (all criteria meet or not ?)
7. install: install the package into the local repository, for use as a dependency in other projects locally (artifact install, webapp/student.war)
8. deploy: done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects. (Applciaiotn run/deploy in some platform)


Clean Phase:
-------------------
1. cleans up artifacts created by prior builds

Site Phase:
-------------------
1. site: generates site documentation for this project

==============================================================================================================================================================================================================

Example on staging:
1. Get clone of studentapp-ui repository --> install maven
# cd studentapp-ui
# mvn package ( Build the package)
# ls /studentapp-ui/target/<new-artifact-created>.war

==============================================================================================================================================================================================================

Pom.xml
----------
1. While building the code, if we required any dependency it will collect it from the pom.xml file.
2. pom.xml file contains the all required information, libreary, plugging, dependancy,
3. Developer wrote the pom.xml
4. we can also add maven dependancy here as per requirment from maven repository.
==============================================================================================================================================================================================================

Jenkins-App: Global Tool configuration
----------------------------------------
here we are adding the maven executable command path to provide the maven enviorment in pipeline
# Add Maven installation
    Name = mvn
    Home_Directory =  /opt/apache-maven-3.9.6

Back to Jenkins server: ==>
----------------------------

# cd /studentapp-ui
# mvn clean package

==> Now add the build stage in our pipeline

Pipeline syntax generator: 
    sh: shell Script
    # mvn clean package  --> generate pipeline Script

    sh 'mvn clean package'   [o/p] --> Add in build Script

('build')
    (sh '/opt/apache-maven/bin/mvn clean package')

##################################################################################################################

Error:
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-war-plugin:2.6:war (default-war)
on project studentapp: Execution default-war of goal org.apache.maven.plugins:maven-war-plugin:2.6:war
failed: Unable to load the mojo 'war' in the plugin 'org.apache.maven.plugins:maven-war-plugin:2.6' 
due to an API incompatibility: org.codehaus.plexus.component.repository.exception.ComponentLookupException: null

==> .mojo is not supported by Java-17, so we need to install java-11 or below.

# java --version
# sudo apt update
# sudo apt install openjdk-11-jdk

To check diffrent version of java or which Java version is curretnly in used. To ensure that use below command.
# sudo update-alternatives --config java
# now to select the exact Java 11 version and Enter.
# Java --version   ... It should be java 11 running
Now, Build it again.
##################################################################################################################




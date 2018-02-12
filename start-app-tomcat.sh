#!/bin/bash
export JAVA_HOME=/home/karol/Dokumenty/Instalki/jdk1.8.0_111
export TOMCAT_PATH=/opt/tomcat
mvn clean install
#mvn spring-boot:run
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
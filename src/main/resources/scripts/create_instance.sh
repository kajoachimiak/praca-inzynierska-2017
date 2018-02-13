#!/bin/bash
TOMCAT_PATH="$1"
GROUP="$2"
EDITION="$3"
COURSE="$4"

cp -R ${TOMCAT_PATH}/apache-tomcat-8.5.27 ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/
sed -i "s/port.http/80${GROUP}/" ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/apache-tomcat-8.5.27/conf/server.xml
sed -i "s/port.shutdown/90${GROUP}/" ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/apache-tomcat-8.5.27/conf/server.xml
sed -i "s/port.ajp/70${GROUP}/" ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/apache-tomcat-8.5.27/conf/server.xml

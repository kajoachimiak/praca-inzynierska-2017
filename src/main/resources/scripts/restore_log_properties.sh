#!/bin/bash
TOMCAT_PATH="$1"
GROUP="$2"
EDITION="$3"
COURSE="$4"

cp ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/apache-tomcat-8.5.27/conf/logging.properties.bak ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/apache-tomcat-8.5.27/conf/logging.properties


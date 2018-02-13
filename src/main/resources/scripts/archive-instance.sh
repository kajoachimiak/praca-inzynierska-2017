#!/bin/bash
TOMCAT_PATH="$1"
GROUP="$2"
EDITION="$3"
COURSE="$4"
BACKUP_DATE="$5"

tar -cvzf ${TOMCAT_PATH}/backups/${COURSE}${EDITION}${GROUP}-${BACKUP_DATE}.tgz ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/apache-tomcat-8.5.27
rm -rf ${TOMCAT_PATH}/instances/${COURSE}${EDITION}${GROUP}/apache-tomcat-8.5.27



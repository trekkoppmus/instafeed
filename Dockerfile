FROM jboss/wildfly
RUN mkdir -p /opt/jboss/wildfly/standalone/deployments
ADD  config/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
ADD  build/libs/instabin.war /opt/jboss/wildfly/standalone/deployments/

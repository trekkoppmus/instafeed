FROM jboss/wildfly
RUN mkdir -p /opt/jboss/wildfly/standalone/deployments
ADD  config/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
ADD  build/libs/instafeed-1.0.war /opt/jboss/wildfly/standalone/deployments/

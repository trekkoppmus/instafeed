FROM jboss/wildfly
RUN mkdir -p /opt/jboss/wildfly/standalone/deployments
ADD  build/libs/instafeed-1.0.war /opt/jboss/wildfly/standalone/deployments/
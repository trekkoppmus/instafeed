FROM jboss/wildfly
ADD  ./build/libs/instafeed-1.0.war /opt/wildfly/standalone/deployments/
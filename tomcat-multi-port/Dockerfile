FROM java:8
MAINTAINER eden

ADD ./target/tomcat-multi-port-*.jar /tomcat-multi-port.jar

EXPOSE 8097

ENTRYPOINT ["java","-jar","/tomcat-multi-port.jar"]
FROM java:8
MAINTAINER eden

ADD ./target/wechat-robot-*.jar /wechat-robot.jar

EXPOSE 8097

ENTRYPOINT ["java","-jar","/wechat-robot.jar"]
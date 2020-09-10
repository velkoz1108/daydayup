FROM java:8
MAINTAINER eden

ADD ./target/weixin-*.jar /weixin.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","/weixin.jar"]
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER haotingfang <haotingfang@foxmail.com>
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD excel-demo-1.0.0-SNAPSHOT.jar excelDemo.jar
# 运行jar包
RUN bash -c 'touch /excelDemo.jar'
ENTRYPOINT ["java","-jar","/excelDemo.jar"]
#VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp。该步骤是可选的，如果涉及到文件系统的应用就很有必要了。/tmp目录用来持久化到 Docker 数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
#项目的 jar 文件作为 “app.jar” 添加到容器的
#ENTRYPOINT 执行项目 app.jar。为了缩短 Tomcat 启动时间，添加一个系统属性指向 “/dev/./urandom” 作为 Entropy Source
#
#如果是第一次打包，它会自动下载java 8的镜像作为基础镜像，以后再制作镜像的时候就不会再下载了。

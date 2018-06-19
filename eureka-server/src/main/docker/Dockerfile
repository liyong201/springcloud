FROM tomcat:latest

# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 创建文件夹
RUN mkdir -p /usr/local/eureka-server/
# 拷贝文件到容器，也可以直接写成ADD eureka-server-0.0.1-SNAPSHOT.jar /app.jar
#COPY ../target/eureka-server-0.0.1-SNAPSHOT.jar /usr/eureka-server/app.jar
ADD eureka-server-0.0.1-SNAPSHOT.jar /usr/local/eureka-server/eureka-server.jar
#RUN bash -c 'touch /app.jar'
# 配置容器启动后执行的命令

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/eureka-server/eureka-server.jar"]
# 开放8761端口
EXPOSE 8761


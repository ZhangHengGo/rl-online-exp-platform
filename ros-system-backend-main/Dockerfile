# 使用官方的Java运行时作为父镜像
FROM openjdk:8-jre-alpine

# 设置工作目录
WORKDIR /app

# 将本地文件复制到容器中
COPY target/ros-system-0.0.1-SNAPSHOT.jar rl-back2.jar


# 定义环境变量
ENV JAVA_OPTS=""

# 设置启动命令
CMD ["java","$JAVA_OPTS","-jar","/app/rl-back2.jar"]
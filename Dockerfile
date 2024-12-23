FROM openjdk:21-slim
ARG APP_NAME=raskub-auth-api

ARG SRC_DIR=/opt/raskub-auth-api
ARG BUILD_DIR=build/libs
ARG JAR_FILE=raskub-auth-api.jar

# memory
ENV JAVA_TOOL_OPTIONS "${JAVA_TOOL_OPTIONS} -XX:InitialRAMPercentage=75.0 -XX:MaxRAMPercentage=75.0"
# gc
ENV JAVA_TOOL_OPTIONS "${JAVA_TOOL_OPTIONS} -XX:+UseG1GC"
# encoding
ENV JAVA_TOOL_OPTIONS "${JAVA_TOOL_OPTIONS} -Dfile.encoding=UTF-8 -Dfile.client.encoding=UTF-8 -Dclient.encoding.override=UTF-8"

COPY ${BUILD_DIR}/${JAR_FILE} ${SRC_DIR}/${JAR_FILE}

WORKDIR ${SRC_DIR}
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "raskub-auth-api.jar"]
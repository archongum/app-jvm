FROM azul/zulu-openjdk-debian:17 as buidler

COPY ./gradle /workspace/gradle
COPY ./gradlew /workspace/
COPY ./gradle.properties /workspace/
COPY ./.editorconfig /workspace/
COPY ./build.gradle.kts /workspace/
COPY ./settings.gradle.kts /workspace/
COPY ./uc /workspace/uc
WORKDIR /workspace
RUN chmod +x ./gradlew && ./gradlew check bootJar

FROM azul/zulu-openjdk-debian:17

COPY --from=buidler /workspace/uc/build/libs/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]

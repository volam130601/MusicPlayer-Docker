# syntax=docker/dockerfile:1.3

# Ref: https://spring.io/guides/topicals/spring-boot-docker/

# BUILD app: docker build -t levidocker123/music_player_docker:latest .
# BUILD only builder: docker build --target builder -t demo:builder .
# RUN app: docker run -p 8080:8080 demo:latest
# Poke around inside the image: docker run -it --entrypoint /bin/bash demo:latest

FROM openjdk:11-jdk-buster as builder
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11-jre-buster
VOLUME /tmp

ADD images /images
VOLUME /images

EXPOSE 8080
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.spring.musicplayer5.MusicPlayer5Application"]
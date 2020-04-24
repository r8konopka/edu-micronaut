#FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/edu-micronaut
WORKDIR /home/app/edu-micronaut

RUN native-image --no-server -cp build/libs/complete-0.1-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/edu-micronaut/edu-micronaut /edu-micronaut/edu-micronaut
ENTRYPOINT ["/edu-micronaut/edu-micronaut"]

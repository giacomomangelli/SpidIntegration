FROM openjdk:8u111-jdk-alpine

RUN apk add --no-cache wget
RUN apk add --no-cache libc6-compat

RUN wget -c --header "Cookie: oraclelicense=accept-securebackup-cookie" https://download.oracle.com/java/21/latest/jdk-21_linux-aarch64_bin.tar.gz
RUN tar xvf jdk-21_linux-aarch64_bin.tar.gz
RUN rm -r -f ./jdk-21_linux-aarch64_bin.tar.gz
RUN mv jdk-21*/ /spid-app

COPY ./target/SpidIntegration*.jar /spid-app/

RUN chmod +x /spid-app/bin/java
RUN mv /spid-app/SpidIntegration*.jar /spid-app/spidIntegration.jar

ENTRYPOINT ["/spid-app/bin/java", "-jar", "/spid-app/spidIntegration.jar"]

EXPOSE 8080/tcp


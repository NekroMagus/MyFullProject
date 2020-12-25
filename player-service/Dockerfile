FROM openjdk:11

VOLUME /tmp

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} scouts.jar

EXPOSE 8443

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait

CMD /wait && java -jar /scouts.jar
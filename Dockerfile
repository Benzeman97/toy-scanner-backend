FROM openjdk:11

COPY ./build/libs/joy-scanner-service.jar joy-scanner-service.jar
#COPY entrypoint.sh /usr/local/bin/entrypoint.sh

EXPOSE 8079
CMD ["java","-jar","joy-scanner-service.jar"]

#RUN chmod +x /usr/local/bin/entrypoint.sh
#CMD bash -c 'source /usr/local/bin/entrypoint.sh && java -Djava.security.egd=file:/dev/./urandom -jar /joy-scanner-service.jar'
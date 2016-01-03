FROM java:8
VOLUME /tmp
ADD gateway-server-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
# Not sure this is a good idea...
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

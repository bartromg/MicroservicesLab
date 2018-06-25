FROM java:8-jre
ADD ./target/hazelcast-cache-client-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/hazelcast-cache-client-0.0.1-SNAPSHOT.jar"]
EXPOSE 5701

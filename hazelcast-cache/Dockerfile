FROM java:8-jre
ADD ./target/hazelcast-cache-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/hazelcast-cache-0.0.1-SNAPSHOT.jar"]
EXPOSE 5701

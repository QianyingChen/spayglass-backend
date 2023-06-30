FROM openjdk:8-jdk
WORKDIR /app
COPY target/spyglass-backend-0.0.1-SNAPSHOT.jar spring-docker.jar
COPY /honeycomb-opentelemetry-javaagent-1.5.2.jar honeycomb.jar
ENV SERVICE_NAME=spyglass-service
ARG HONEYCOMB_API_KEY
ENV HONEYCOMB_METRICS_DATASET=my-spyglass-metrics
EXPOSE 8080
CMD ["java", "-javaagent:honeycomb.jar", "-jar", "spring-docker.jar"]

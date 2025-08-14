FROM eclipse-temurin:24-jre

WORKDIR /app

COPY target/doctors_appointment_system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]

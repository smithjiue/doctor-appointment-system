FROM eclipse-temurin:24-jre

WORKDIR /app

COPY target/doctor-appointments-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]

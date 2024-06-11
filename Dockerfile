FROM eclipse-temurin:17
EXPOSE 8080
ADD build/libs/QRManagementService*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
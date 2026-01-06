# ---------- Stage 1: Build ----------
FROM  hub.hamdocker.ir/maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# کپی pom و دانلود dependencies (با cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# کپی کد و بیلد jar اجرایی
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Stage 2: Runtime ----------
FROM  hub.hamdocker.ir/eclipse-temurin:21-jre
WORKDIR /app

# کپی jar اجرایی از مرحله build
COPY --from=build /app/target/*-exec.jar app.jar

EXPOSE 8586

# اجرای Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]

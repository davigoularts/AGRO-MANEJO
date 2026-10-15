# Etapa de build: compila a API com Maven e JDK 25 dentro do container,
# então ninguém precisa ter Java instalado no computador.
FROM maven:3.9-eclipse-temurin-25 AS build
# Define a pasta de trabalho dentro do container de build.
WORKDIR /app
# Copia a configuração do Maven e o código-fonte (o mvnw não é usado aqui).
COPY pom.xml .
COPY src ./src
# Gera o jar executável. O cache do Maven sobrevive entre builds, então as
# dependências só são baixadas na primeira vez.
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests package

# Etapa final: só o Java necessário para rodar o jar, sem Maven nem código-fonte.
FROM eclipse-temurin:25-jre
# Define a pasta de trabalho da aplicação.
WORKDIR /app
# Copia o jar gerado na etapa de build.
COPY --from=build /app/target/*.jar app.jar
# Documenta a porta em que a API escuta.
EXPOSE 8080
# Sobe a API.
ENTRYPOINT ["java", "-jar", "app.jar"]

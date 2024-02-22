# Usa l'immagine ufficiale di Java come base
FROM openjdk:17
COPY * ddd/
# Copia il file JAR del tuo microservizio nel container
COPY noleggioService/target/noleggioService.jar app.jar

# Espone la porta su cui il tuo microservizio sarà in ascolto
EXPOSE 8082

# Comando per eseguire l'applicazione
CMD ["java","-jar","/app.jar"]

# Usa l'immagine ufficiale di Java come base
FROM openjdk:17
# Copia il file JAR del tuo microservizio nel container
COPY impiantoService/target/impiantoService.jar app.jar

# Espone la porta su cui il tuo microservizio sarà in ascolto
EXPOSE 8083

# Comando per eseguire l'applicazione
CMD ["java","-jar","/app.jar"]

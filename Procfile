COPY --chown=185 heroku.sh /target/quarkus-app/
web: RUN target/quarkus-app/heroku.sh && java -Dquarkus.http.port=$PORT $JAVA_OPTS -jar target/quarkus-app/quarkus-run.jar
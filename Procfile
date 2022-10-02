RUN ls && echo "teste"
COPY --chown=185 heroku.sh target/quarkus-app/
RUN ls target/quarkus-app/ && echo "teste"
web: ./target/quarkus-app/heroku.sh && java -Dquarkus.http.port=$PORT $JAVA_OPTS -jar target/quarkus-app/quarkus-run.jar
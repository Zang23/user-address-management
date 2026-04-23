FROM gvenzl/oracle-xe:21-slim

ENV ORACLE_PASSWORD=123456

# copia jar
COPY target/*.jar /app.jar

# copia script
COPY start.sh /start.sh

RUN chmod +x /start.sh

EXPOSE 8080

CMD ["/start.sh"]
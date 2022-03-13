FROM openjdk:8 as release
EXPOSE 8889
COPY maven/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]

## the following two lines are not recommened
# TO RUN THIS IMAGE WITH POSTGRESS YOU HAVE TO UNCOMMENT THE FOLLOWING TWO LINES
#RUN cp /usr/lib/postgresql/9.3/bin/postgres /usr/bin/postgres
#CMD runuser -l postgres -c 'postgres -D/var/lib/postgresql/9.3/main -cconfig_file=/etc/postgresql/9.3/main/postgresql.conf'& java -jar /app/target/*.jar

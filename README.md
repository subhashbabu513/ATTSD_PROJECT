#TeacherStudent application

## Build commands

| task | description |
| -----| ------------ |
| clean | deletes directory *target* |
| test | runs unit test cases using in-memory H2 database |
| install | creates jar, builds a docker image. |
| exec:java | runs the application using h2 db |


## Test against postgres

### Start postgres db

```
docker run -d -ePOSTGRES_USER=postgres -ePOSTGRES_PASSWORD=postgres -ePOSTGRES_DB=attsw -p5432:5432 postgres:10
```

### Start maven application
```
./mvnw -Dspring.config.location=postgres.properties
```

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=adexam&metric=alert_status)](https://sonarcloud.io/dashboard?id=adexam)

Details:

Jenkins - http://16.170.128.41:8080/job/attsw_exam/  (Username: admin/Password: admin123)
Postgres - 16.170.128.41:5432  (Username: postgres/Password: postgress)


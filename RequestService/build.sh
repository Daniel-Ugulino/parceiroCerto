export DBHOST=localhost
export DBPASSWORD=ugulino10
export DBSOURCE=parceiroCertoDB
export DBUSER=postgres
export DBPORT=5432
export RBHOST=localhost
export RBPASSWORD=guest
export RBPORT=5672
export RBUSER=guest
export DTSOURCE=src/main/java/com/example/requestservice/Dataloader/Data/
export EUREKASERVER=http://localhost:8761/eureka/

./mvnw clean package -DskipTests

docker build -t requestservice:latest .
docker tag requestservice:latest danielugulino/requestservice:latest
docker push danielugulino/requestservice:latest
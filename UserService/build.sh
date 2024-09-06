export DBHOST=localhost
export DBPASSWORD=ugulino10
export DBSOURCE=parceiroCertoDB
export DBUSER=postgres
export DBPORT=5432
export DTSOURCE=src/main/java/com/example/userservice/Dataloader/Data/
export EUREKASERVER=http://localhost:8761/eureka/

./mvnw clean package

docker build -t userservice:latest .
docker tag userservice:latest danielugulino/userservice:latest
docker push danielugulino/userservice:latest
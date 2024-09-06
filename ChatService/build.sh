export DBHOST=localhost
export DBPASSWORD=ugulino10
export DBSOURCE=parceiroCertoDB
export DBUSER=postgres
export DBPORT=5432
export EUREKASERVER=http://localhost:8761/eureka/

./mvnw clean package

docker build -t chatservice:latest .
docker tag chatservice:latest danielugulino/chatservice:latest
docker push danielugulino/chatservice:latest
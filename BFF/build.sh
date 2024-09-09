export EUREKASERVER=http://localhost:8761/eureka/

./mvnw clean package -DskipTests

docker build -t bff:latest .
docker tag bff:latest danielugulino/bff:latest
docker push danielugulino/bff:latest
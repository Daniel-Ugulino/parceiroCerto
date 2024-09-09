export EUREKASERVER=http://localhost:8761/eureka/
./mvnw clean package -DskipTests
docker build -t apigateway:latest .
docker tag apigateway:latest danielugulino/apigateway:latest
docker push danielugulino/apigateway:latest
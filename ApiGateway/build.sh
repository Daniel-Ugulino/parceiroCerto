./mvnw clean package
docker build -t apigateway:latest .
docker tag apigateway:latest danielugulino/apigateway:latest
docker push danielugulino/apigateway:latest
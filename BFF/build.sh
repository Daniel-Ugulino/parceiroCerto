./mvnw clean package

docker build -t bff:latest .
docker tag bff:latest danielugulino/bff:latest
docker push danielugulino/bff:latest
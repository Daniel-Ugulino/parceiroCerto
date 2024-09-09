export EUREKAHOST=localhost
./mvnw clean package
docker build -t serverregistry:latest .
docker tag serverregistry:latest danielugulino/serverregistry:latest
docker push danielugulino/serverregistry:latest
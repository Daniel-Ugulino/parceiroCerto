export DBHOST=localhost
export DBPASSWORD=ugulino10
export DBSOURCE=parceiroCertoDB
export DBUSER=postgres
export DBPORT=5432

./mvnw clean package

docker build -t authservice:latest .
docker tag authservice:latest danielugulino/authservice:latest
docker push danielugulino/authservice:latest
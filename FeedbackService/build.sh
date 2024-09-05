export DBHOST=localhost
export DBPASSWORD=ugulino10
export DBSOURCE=parceiroCertoDB
export DBUSER=postgres
export DBPORT=5432

./mvnw clean package

docker build -t feedbackservice:latest .
docker tag feedbackservice:latest danielugulino/feedbackservice:latest
docker push danielugulino/feedbackservice:latest

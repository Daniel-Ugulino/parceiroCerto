export DBHOST=localhost
export DBPASSWORD=ugulino10
export DBSOURCE=parceiroCertoDB
export DBUSER=postgres
export DBPORT=5432

export RBPASSWORD=guest
export RBPORT=5672
export RBUSER=guest
export DTSOURCE=src/main/java/com/example/taskservice/Dataloader/Data/
./mvnw clean package

docker build -t taskservice:latest .
docker tag taskservice:latest danielugulino/taskservice:latest
docker push danielugulino/taskservice:latest
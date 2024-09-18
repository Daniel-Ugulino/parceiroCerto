kubectl delete all --all
sleep 15
kubectl apply -f postgis.yml
echo 'RUNNING postgis '
sleep 30
kubectl apply -f rabbitMq.yml
echo 'RUNNING rabbitMq '
sleep 10
kubectl apply -f zipkin.yml
echo 'RUNNING zipkin '
sleep 10
kubectl apply -f serverRegistry.yml
echo 'RUNNING serverRegistry '
sleep 10
kubectl apply -f feedbackService.yml
echo 'RUNNING feedbackService '
sleep 10
kubectl apply -f bff.yml
echo 'RUNNING bff '
sleep 10
kubectl apply -f authService.yml
echo 'RUNNING authService '
sleep 10
kubectl apply -f userService.yml
echo 'RUNNING userService '
sleep 40
kubectl apply -f taskService.yml
echo 'RUNNING taskService '
sleep 30
kubectl apply -f requestService.yml
echo 'RUNNING requestService '
sleep 30
kubectl apply -f chatService.yml
echo 'RUNNING chatService '
sleep 10
kubectl apply -f apiGateway.yml
echo 'RUNNING apiGateway '
sleep 20
echo 'RUNNING Port-Fowards'
kubectl port-forward deployment/api-gateway 8080:8080 & \
kubectl port-forward deployment/rabbitMq 15672:15672 & \
kubectl port-forward deployment/serverRegistry 8761:8761 & \
kubectl port-forward deployment/postgis 5432:5432
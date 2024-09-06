kubectl apply -f postgis.yml
sleep 40
kubectl apply -f rabbitMq.yml
sleep 10
kubectl apply -f serverRegistry.yml
sleep 10
kubectl apply -f feedbackService.yml
sleep 10
kubectl apply -f bff.yml
sleep 10
kubectl apply -f authService.yml
sleep 10
kubectl apply -f userService.yml
sleep 50
kubectl apply -f taskService.yml
sleep 50
kubectl apply -f chatService.yml
sleep 10
kubectl apply -f apiGateway.yml
sleep 20

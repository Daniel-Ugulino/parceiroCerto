kubectl apply -f postgis.yml
sleep 50
kubectl apply -f rabbitMq.yml
sleep 10
kubectl apply -f chatService.yml
kubectl apply -f feedbackService.yml
kubectl apply -f bff.yml
kubectl apply -f authService.yml
kubectl apply -f apiGateway.yml
kubectl apply -f userService.yml
sleep 60
kubectl apply -f taskService.yml
sleep 120

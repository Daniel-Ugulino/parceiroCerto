package com.example.taskservice.Utils;

public class DistaceMeasure {
    private static final double RAIO_TERRA = 6371.0;

    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double phi1 = Math.toRadians(lon1);
        double phi2 = Math.toRadians(lon2);

        double deltaTheta = theta2 - theta1;
        double deltaPhi = phi2 - phi1;

        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2) +
                Math.cos(theta1) * Math.cos(theta2) *
                        Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        return RAIO_TERRA * c;
    }
}

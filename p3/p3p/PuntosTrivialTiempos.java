package p3p;

import java.util.ArrayList;
import java.util.Random;

public class PuntosTrivialTiempos {

    static class Punto {
        double x;
        double y;

        Punto(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        System.out.println("n\tTiempo (ms)");

        for (int n = 1024; n <= 131072; n *= 2) {

            ArrayList<Punto> puntos = new ArrayList<>();
            Random rand = new Random();

            // Generar puntos aleatorios
            for (int i = 0; i < n; i++) {
                double x = rand.nextDouble() * 100;
                double y = rand.nextDouble() * 100;
                puntos.add(new Punto(x, y));
            }

            long inicio = System.currentTimeMillis();

            double distanciaMinima = Double.MAX_VALUE;

            // Algoritmo trivial O(n^2)
            for (int i = 0; i < puntos.size(); i++) {
                for (int j = i + 1; j < puntos.size(); j++) {

                    Punto p1 = puntos.get(i);
                    Punto p2 = puntos.get(j);

                    double dx = p1.x - p2.x;
                    double dy = p1.y - p2.y;
                    double distancia = Math.sqrt(dx * dx + dy * dy);

                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                    }
                }
            }

            long fin = System.currentTimeMillis();
            long tiempoMs = fin - inicio;

            System.out.println(n + "\t" + tiempoMs);
        }
    }
}
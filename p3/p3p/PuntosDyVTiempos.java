package p3p;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuntosDyVTiempos {

    static class Punto {
        double x, y;

        Punto(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static Punto mejorP1;
    static Punto mejorP2;
    static double mejorDist;

    public static void main(String[] args) {

        int n = 1024;

        System.out.println("n\ttiempo");

        while (true) {

            ArrayList<Punto> puntos = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                puntos.add(new Punto(rand.nextDouble() * 100, rand.nextDouble() * 100));
            }

      
            mejorDist = Double.MAX_VALUE;
            mejorP1 = null;
            mejorP2 = null;

            
            long inicio = System.currentTimeMillis();

           
            puntos.sort((a, b) -> Double.compare(a.x, b.x));

            dyv(puntos);

            long fin = System.currentTimeMillis();
            long tiempoMs = fin - inicio;

            if (tiempoMs > 60000) {
                System.out.println(n + "\tFdT");
                break;
            } else {
                System.out.println(n + "\t" + tiempoMs);
            }

            n *= 2;
        }
    }

    static void dyv(List<Punto> puntos) {

        int n = puntos.size();
        int mitad = n / 2;

        ArrayList<Punto> izq = new ArrayList<>(puntos.subList(0, mitad));
        ArrayList<Punto> der = new ArrayList<>(puntos.subList(mitad, n));

        dyv(izq);
        dyv(der);

        for (Punto p1 : izq) {
            for (Punto p2 : der) {
                double d = distancia(p1, p2);
                if (d < mejorDist) {
                    mejorDist = d;
                    mejorP1 = p1;
                    mejorP2 = p2;
                }
            }
        }
    }

    static double distancia(Punto a, Punto b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
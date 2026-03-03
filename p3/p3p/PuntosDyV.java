package p3p;

import java.io.*;
import java.util.*;

public class PuntosDyV {

    static class Punto {
        double x, y;

        Punto(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static Punto mejorP1;
    static Punto mejorP2;
    static double mejorDist = Double.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Punto> puntos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String linea = br.readLine();
            String[] partes = linea.split(",");
            double x = Double.parseDouble(partes[0]);
            double y = Double.parseDouble(partes[1]);
            puntos.add(new Punto(x, y));
        }
        br.close();

        // ordenar por X
        puntos.sort((a, b) -> Double.compare(a.x, b.x));

        dyv(puntos);

        System.out.println("PUNTOS MÁS CERCANOS: [" 
                + String.format("%.6f", mejorP1.x) + ", " 
                + String.format("%.6f", mejorP1.y) + "] [" 
                + String.format("%.6f", mejorP2.x) + ", " 
                + String.format("%.6f", mejorP2.y) + "] SU DISTANCIA MÍNIMA= " 
                + String.format("%.6f", mejorDist));
    }

    static void dyv(List<Punto> puntos) {

        int n = puntos.size();

        int mitad = n / 2;
        List<Punto> izq = puntos.subList(0, mitad);
        List<Punto> der = puntos.subList(mitad, n);

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
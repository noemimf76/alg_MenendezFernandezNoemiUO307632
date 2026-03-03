package p3p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PuntosTrivial {

    static class Punto {
        double x;
        double y;

        Punto(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("[%.6f, %.6f]", x, y);
        }
    }

    public static void main(String[] args) {

        String nombreFichero = args[0];
        ArrayList<Punto> puntos = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {

		int n = Integer.parseInt(br.readLine().trim());

		for (int i = 0; i < n; i++) {
			String linea = br.readLine();
			String[] partes = linea.split(",");

			double x = Double.parseDouble(partes[0]);
			double y = Double.parseDouble(partes[1]);

			puntos.add(new Punto(x, y));
		}

		} catch (IOException e) {
			System.out.println("Error leyendo el fichero: " + e.getMessage());
		}

        double distanciaMinima = Double.MAX_VALUE;
        Punto p1Min = null;
        Punto p2Min = null;

        for (int i = 0; i < puntos.size(); i++) {
            for (int j = i + 1; j < puntos.size(); j++) {

                Punto p1 = puntos.get(i);
                Punto p2 = puntos.get(j);

                double dx = p1.x - p2.x;
                double dy = p1.y - p2.y;
                double distancia = Math.sqrt(dx * dx + dy * dy);

                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    p1Min = p1;
                    p2Min = p2;
                }
            }
        }

        // Mostrar resultado
        System.out.println("PUNTOS MÁS CERCANOS: " 
                + p1Min + " " + p2Min 
                + " SU DISTANCIA MÍNIMA= " 
                + String.format("%.6f", distanciaMinima));
    }
}
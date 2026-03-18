import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DevoradorTiempos {
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		for(int n = 8;n<=65536;n*=2){
			String archivo = "sols/g" + n + ".json";
			try (FileReader reader = new FileReader(archivo)) {
				JSONObject jsonObject = (JSONObject) parser.parse(reader);
				@SuppressWarnings("unchecked")
				Map<String, List<String>> grafo = (Map<String, List<String>>) jsonObject.get("grafo");

				long inicio = System.currentTimeMillis();
                Map<String, String> solucion = ColoreoGrafo.realizarVoraz(grafo);
                long fin = System.currentTimeMillis();

                double tiempoMs = (fin - inicio);
                System.out.println("Grafo: " + archivo + " -> Tiempo: " + tiempoMs + " ms");
                //System.out.println("Colores usados: " + new HashSet<>(solucion.values()).size());
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
}

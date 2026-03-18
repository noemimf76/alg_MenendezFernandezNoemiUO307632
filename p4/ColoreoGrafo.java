import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ColoreoGrafo {


    private static String[] colors = {"red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "lime"};

    public static Map<String, String> realizarVoraz(Map<String,List<String>> grafo) {
        Map<String,String> grafoColoreado = new HashMap<String,String>();
        for(String nodo: grafo.keySet()){
            Set<String> coloresUsados = new HashSet<String>();
            for(Object nodoAdyacenteObj: grafo.get(nodo)){
                String nodoAdyacente = String.valueOf(nodoAdyacenteObj);
                String colorAdyacente = grafoColoreado.get(nodoAdyacente);
                if(colorAdyacente != null){
                    coloresUsados.add(colorAdyacente);
                }
            }
            int colorIndice = 0;
            while(coloresUsados.contains(colors[colorIndice])){
                colorIndice++;
            }
            grafoColoreado.put(nodo, colors[colorIndice]);
        }
        return grafoColoreado;
    }

}

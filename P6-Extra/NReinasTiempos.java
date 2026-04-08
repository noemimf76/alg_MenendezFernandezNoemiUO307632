import java.io.IOException;

public class NReinasTiempos{


    public static void main(String[] args) throws IOException{
        NReinas_UO ac = new NReinas_UO();
        for(int i=1;i<=20;i++){
            System.out.print("Tiempo del caso de prueba " + i +": ");
            long inicio = System.currentTimeMillis();
            ac.resolverNReinas(i);
            long fin = System.currentTimeMillis();
            System.out.println(fin-inicio + " ms");
        }
    }
}

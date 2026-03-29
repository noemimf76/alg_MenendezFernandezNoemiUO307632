import java.io.IOException;

public class AlmacenajeContenedoresTiempos{


    public static void main(String[] args) throws IOException{
        AlmacenajeContenedores ac = new AlmacenajeContenedores();
        String fichero;
        for(int i=0;i<=9;i++){
            fichero = "CasosPrueba/test0" + i +".txt";
            System.out.print("Tiempo del fichero de prueba " + fichero +": ");
            long inicio = System.currentTimeMillis();
            ac.solve(fichero);
            long fin = System.currentTimeMillis();
            System.out.println(fin-inicio + " ms");
        }
    }
}
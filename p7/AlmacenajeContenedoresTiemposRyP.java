import java.io.IOException;

public class AlmacenajeContenedoresTiemposRyP{


    public static void main(String[] args) throws IOException{
        AlmacenajeContenedoresRyP ac = new AlmacenajeContenedoresRyP();
        String fichero;
        for(int i=0;i<=9;i++){
            fichero = "test0" + i +".txt";
            System.out.print("Tiempo del fichero de prueba " + fichero +": ");
            long total = 0;
            for(int j=0;j<100;j++){
                
                long inicio = System.currentTimeMillis();
                ac.solve(fichero);
                long fin = System.currentTimeMillis();
                total += fin -inicio;
            }
            System.out.println(total + " ms");
            
        }
    }
}


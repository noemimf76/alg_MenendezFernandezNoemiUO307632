
import java.util.List;


public class Ferry{

    private int len;    //longitud de los carriles del barco
    private List<Integer> vehicles; // lista de vehículos
    private boolean[][] dp; // matriz con las posibles soluciones
    private int[] sumatorio; // suma acumulada de las longitudes de os vehiculos
    
    public Ferry(int len, List<Integer> vehicles){
        this.len = len;
        this.vehicles = vehicles;
        dp = new boolean[vehicles.size()+1][len+1];
        this.sumatorio = new int[vehicles.size()+1];
        this.sumatorio[0] = 0;
        for(int i=1;i<=vehicles.size();i++){
            this.sumatorio[i] += this.sumatorio[i-1] + vehicles.get(i-1);
        }


    }

    public void run(){
        boolean terminar = false;
        //inicio primera fila
        dp[0][0] = true;
        for(int j=1;j<=len;j++){
           dp[0][j] = false; 
        }

        for(int i=1;i<=vehicles.size();i++){
            for(int p=0;p<=len;p++){    
                terminar = true;
                if(dp[i-1][p] == true){
                    //colocar en babor
                    if(p+vehicles.get(i) <= len){
                        dp[i][p+vehicles.get(i)] = true;
                    }
                    //colocar en estribor
                    if(sumatorio[i+1]-p <= len){
                        dp[i][p] = true;
                    }
                }
                if(dp[i][p]==true){
                    terminar = false;
                }
            }
            if(terminar == true){
                
            }

        }
    }

}





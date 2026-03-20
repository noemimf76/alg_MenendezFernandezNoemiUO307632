import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Ferry{

    private int len;    //longitud de los carriles del barco
    private List<Integer> vehicles; // lista de vehículos
    private boolean[][] dp; // matriz con las posibles soluciones
    private int[] sumatorio; // suma acumulada de las longitudes de os vehiculos
    private List<Step> path; //variable para guardar el camino seleccionado
    
    public Ferry(int len, List<Integer> vehicles){
        this.len = len;
        this.vehicles = vehicles;
        dp = new boolean[vehicles.size()+1][len+1];
        this.sumatorio = new int[vehicles.size()+1];
        this.sumatorio[0] = 0;
        for(int i=1;i<=vehicles.size();i++){
            this.sumatorio[i] += this.sumatorio[i-1] + vehicles.get(i-1);
        }
        this.path = new ArrayList<Step>();
    }

    /**
    * Devuelve el numero máximo de vehiculos posibles
    * l (siendo l < boatlength) con dp[i][l] = true. es el maximo número de coches que pueden entrar.
    */
    public int getMaximumNumberOfVehicles() {
        for (int i = vehicles.size(); i >= 0; i--) {
            for (int l = 0; l <= len; l++) {
                if (dp[i][l]) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void printData() {
		System.out.printf("Length of parallel lanes for starboard and port on the ferry: %d\n", len);
		System.out.printf("The vehicles have the following lengths:\n");
		for (int i = 0; i < vehicles.size(); i++) {
			System.out.printf("\tVehicle %d: %d\n", i+1, vehicles.get(i));
		}
	}

    public void printPossibleAssignation() {
		boolean found = false;
		System.out.printf("\nPossible assignation:\n");
		for (int i = getMaximumNumberOfVehicles(); i > 0; i--) {
            if (found) break;
            for (int p = 0; p <= len; p++) {
                if (found) break;
                if (dp[i][p]) {          
                    found = true;
                    processAssignation(i, p);
                }
            }
        }
			//si found es true -> rompo la ejecución
			//para cada p de la longitud del barco
			//		si found es true -> rompo la ejecución
			//		si dp[i][p-v(i)] es true -> found = true; llamo a processAssignation()
	}
	

    private void processAssignation(int i, int l) {

        // if ((i == 0) && (l == 0)) { // llamo a printPath y acabo la ejecución (return)
         if ((i == 0) && (l == 0)) {
                printPath();
                return;
        }
        //if (dp[i-1][l]) {
        //		añado al path (path.addFirst) un nuevo Step llamado estribor; llamo a processAssignation(i-1, l);
        if (dp[i - 1][l]) {
            path.addFirst(new Step(i - 1, l, i, l, i, "estribor"));
            processAssignation(i - 1, l);
            
            
        }
            
        // if (dp[i-1][l-vehicles.get(i-1)]) {
        //		añado al path (path.addFirst) un nuevo Step llamado babor; llamo a processAssignation(i-1, l-vehicles.get(i-1));
        
        else if (l >= vehicles.get(i - 1) && dp[i - 1][l - vehicles.get(i - 1)]) {
            path.addFirst(new Step(i - 1, l - vehicles.get(i - 1), i, l, i, "babor"));
            processAssignation(i - 1, l - vehicles.get(i - 1));
            
            
        }
        
    }

	

    public void printSolutionTable() {
        System.out.printf("\nTable with calculations:\n");
            
        System.out.printf("%4s", "V/L");
        for (int i = 0; i <= len; i++) {
            System.out.printf("%4d", i);	
        }
        System.out.printf("\n");
            
        for (int i = 0; i <= vehicles.size(); i++) {
            System.out.printf("%4d", i);
            for (int l = 0; l <= len; l++) {
                if (dp[i][l]){				
                    System.out.printf("%4s", "T");
                }
                else{ 
                        System.out.printf("%4s", "F");
                    }
            }
            System.out.printf("\n");
        }
    }

    private void printPath() {
        int portLength = 0;
        int starboardLength = 0;
        for (var step : path) {		
            if (step.movement().equals("babor")){
                portLength += vehicles.get(step.vehicle()-1);
            }
            else{
                starboardLength += vehicles.get(step.vehicle()-1);
            }
            System.out.printf("Vehicle %d (length %d) -- From (%d, %d) -- To (%d, %d) -- Position: %s -- Port lengh: %d -- Starboard length: %d\n", 
                    step.vehicle(), vehicles.get(step.vehicle()-1),
                    step.previousI(), step.previousL(),
                    step.currentI(), step.currentL(), 
                    step.movement(), portLength, starboardLength);
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
            terminar = true;
            for(int p=0;p<=len;p++){    
                
                if(dp[i-1][p] == true){
                    //colocar en babor
                    if(p+vehicles.get(i-1) <= len){
                        dp[i][p+vehicles.get(i-1)] = true;
                    }
                    //colocar en estribor
                    if(sumatorio[i]-p <= len){
                        dp[i][p] = true;
                    }
                }
                if(dp[i][p]==true){
                    terminar = false;
                }
            }
            if(terminar == true){
                break;
            }

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        int length = Integer.valueOf(reader.readLine());
        List<Integer> vehicles = new ArrayList<Integer>();
        for (String s : reader.readLine().split(" ")) {
            vehicles.add(Integer.valueOf(s));
        }
        reader.close();

        Ferry ferry = new Ferry(length, vehicles);
        ferry.run();
        System.out.printf("\nHan llegado un total de %d vehículos (%d viajarán).\n", vehicles.size(), ferry.getMaximumNumberOfVehicles());
        ferry.printSolutionTable();
        ferry.printPossibleAssignation();
    }
}

record Step(int previousI, int previousL, 
		int currentI, int currentL, 
		int vehicle, String movement) {}





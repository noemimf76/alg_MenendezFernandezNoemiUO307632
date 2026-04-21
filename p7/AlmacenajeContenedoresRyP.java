import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class AlmacenajeContenedoresRyP {

    private int containerCapacity;
    private int[] objects;
    private int[] bestSolution;
    private int bestContainers;

    
    public static void main(String[] args) throws IOException {
        
        AlmacenajeContenedoresRyP ac = new AlmacenajeContenedoresRyP();
        ac.solve(args[0]);
        ac.printSolution();
    }

    private void printSolution() {
        System.out.println("Lista de contenedores y objetos obtenidos:");
        
        for(int i=0;i<bestContainers;i++){
            System.out.print("Contenedor " + (i + 1) + ": " );
            for (int j = 0; j < bestSolution.length; j++) {
                if (bestSolution[j] == i) {
                    System.out.print(objects[j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("El número de contenedores necesario es " + bestContainers);
    }

    public void solve(String fichero) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fichero));
        containerCapacity = Integer.valueOf(reader.readLine());
        String[] parts = reader.readLine().split(" ");
        objects = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            objects[i] = Integer.valueOf(parts[i]);
        }
        reader.close();
        calculateMinNumContainers(containerCapacity, objects);
    }

    private void calculateMinNumContainers(int containerCapacity, int[] objects) {
        int[] solution = new int[objects.length];
        bestSolution = new int[objects.length];
        bestContainers = objects.length;
        int[] containerLoad = new int[objects.length];
        backtracking(objects, containerCapacity, solution, containerLoad,0,0);
    }

    private void backtracking(int[] objects, int capacity, int[] solution, int[] containerLoad, int index, int numContainers) {

        //poda
        int extra = 0;
        for(int i=index;i<objects.length;i++){
            extra += objects[i];
        }
        int cotaMin = (extra + capacity -1)/capacity;
        if(numContainers + cotaMin >= bestContainers){
            return;
        }
        
        if (index == objects.length) {
            if (numContainers < bestContainers) {
                bestContainers = numContainers;
                bestSolution = solution.clone();
            }
            return;
        }


        for (int c = 0; c < numContainers; c++) {
            if (containerLoad[c] + objects[index] <= capacity) {
                solution[index] = c;
                containerLoad[c] += objects[index];

                backtracking(objects, capacity, solution, containerLoad, index + 1, numContainers);

                containerLoad[c] -= objects[index]; 
            }
        }

        solution[index] = numContainers;
        containerLoad[numContainers] += objects[index];

        backtracking(objects, capacity, solution, containerLoad, index + 1, numContainers + 1);

        containerLoad[numContainers] -= objects[index]; 
    }
}

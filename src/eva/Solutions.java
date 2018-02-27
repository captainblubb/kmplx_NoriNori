package eva;

import base.Cell;
import base.MatrixCreator;
import base.Section;

import java.util.ArrayList;

public class Solutions {

    private int fitness;
    Cell[][] matrix;

    public Solutions(){

        matrix = MatrixCreator.createMatrixFromTask();
        this.fitness=0;

    }

    public void calculateFitness(){
        int fitnessValue =0;
        ArrayList<Section> sections = new ArrayList<>();
    }

    public void calculateChromosom(){

    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}

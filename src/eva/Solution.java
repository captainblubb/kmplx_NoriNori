package eva;

import base.Cell;
import base.MatrixCreator;
import base.Section;
import configuration.Configuration;
import configuration.MersenneTwister;

import java.util.ArrayList;

public class Solution {

    private int fitness;

    private int[][] chromaMatrix;
    private Cell[][] matrix;
    private ArrayList<Section> sections;

    public Solution() {
        chromaMatrix= new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];
        matrix = MatrixCreator.createMatrixFromTask();
        sections = MatrixCreator.getSectionsOfLastMatrix();
        this.fitness = 0;
        initRandomDominos();
    }

    public void placeRandomDominos(){
        initRandomDominos();
    }

    public void placeDominosByChromaMatrix(int[][] newChromaMatrix){
        this.chromaMatrix= newChromaMatrix;

        for(int x = 0; x<Configuration.GRID_SIZE; x++){
            for(int y = 0; y<Configuration.GRID_SIZE; y++){
                if(newChromaMatrix[x][y] == 0){
                    if(matrix[x][y].isDominoSet()){
                        Cell cell = matrix[x][y].removeDomino().removeDomino();
                    }
                }else{
                    if(x>0 && newChromaMatrix[x-1][y] == newChromaMatrix[x][y]){
                        matrix[x][y].setDomino(matrix[x-1][y]);
                        matrix[x-1][y].setDomino(matrix[x][y]);

                    }else if(x<(Configuration.GRID_SIZE-1) && newChromaMatrix[x+1][y] == newChromaMatrix[x][y]){
                        matrix[x][y].setDomino(matrix[x+1][y]);
                        matrix[x+1][y].setDomino(matrix[x][y]);

                    }else if(y>0 && newChromaMatrix[x][y-1] == newChromaMatrix[x][y]){
                        matrix[x][y].setDomino(matrix[x][y-1]);
                        matrix[x][y-1].setDomino(matrix[x][y]);

                    }else if(y<(Configuration.GRID_SIZE-1) && newChromaMatrix[x][y+1] == newChromaMatrix[x][y+1]){
                        matrix[x][y].setDomino(matrix[x][y+1]);
                        matrix[x][y+1].setDomino(matrix[x][y]);
                    }
                }
            }

        }

    }


    public int[][] getChromaMatrix() {
        return chromaMatrix;
    }
    public int getFitness() {
        return fitness;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public void calculateChromosom() {
        int[][] chromaMatrixCalc = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];
        int dominoCounter=1;
        for (int y = 0; y < Configuration.GRID_SIZE; y++) {
             for (int x = 0; x < Configuration.GRID_SIZE; x++) {
                //Wenn in der Matrix noch keine Domino eingetragen ist
                if (chromaMatrixCalc[x][y]==0) {
                    if(matrix[x][y].isDominoSet()){
                    chromaMatrixCalc[x][y] = dominoCounter;
                    chromaMatrixCalc[matrix[x][y].getDominoSecondCell().getRowPos()][matrix[x][y].getDominoSecondCell().getColPos()]= dominoCounter;
                    dominoCounter++;
                    }
                }
            }
        }
        chromaMatrix = chromaMatrixCalc;
    }

    public String chromosomToString(){
        String chromosomString = "";
        for (int y = 0; y<Configuration.GRID_SIZE;y++){
            for(int x = 0; x<Configuration.GRID_SIZE;x++){
                chromosomString=chromosomString+ "["+chromaMatrix[x][y]+"] ";
            }
            chromosomString=chromosomString+"\n";
        }
        return chromosomString;
    }

    public void calculateFitness() {
        int fitnessValue = 0;

        // Pro Section = 2 Domino +50 Fitness
        for (int sectionIndex = 0; sectionIndex < sections.size(); sectionIndex++) {
            if (sections.get(sectionIndex).getDominoCount() == 2) {
                fitnessValue += 50;
            }
        }

        //Pro Domino ohne angrenzendesDomino
        for (int x = 0; x < Configuration.GRID_SIZE; x++) {
            for (int y = 0; y < Configuration.GRID_SIZE; y++) {

                //Wenn Domino gesetzt ist umliegende betrachten
                Cell currentCell = matrix[x][y];
                if (currentCell.isDominoSet()) {
                    int otherDominosNextTo = -1;

                    if (x > 0) {
                        if (matrix[x - 1][y].isDominoSet()) {
                            otherDominosNextTo++;
                        }
                    }


                    if (x < 9) {
                        if (matrix[x + 1][y].isDominoSet()) {
                            otherDominosNextTo++;
                        }
                    }

                    if (y > 0) {
                        if (matrix[x][y - 1].isDominoSet()) {
                            otherDominosNextTo++;
                        }
                    }

                    if (y < 9) {
                        if (matrix[x][y + 1].isDominoSet()) {
                            otherDominosNextTo++;
                        }
                    }

                    fitnessValue += (20) * (3 - otherDominosNextTo);
                }
            }
        }

        this.fitness = fitnessValue;
    }

    private void initRandomDominos() {
        MersenneTwister mt = new MersenneTwister();
        int dominoCounter = 0;
        while (dominoCounter < sections.size()) {
            int x = mt.nextInt(0, Configuration.GRID_SIZE);
            int y = mt.nextInt(0, Configuration.GRID_SIZE);
            Cell currentCell = matrix[x][y];

            if (currentCell.isDominoSet() == false) {

                //Um gleichverteilung der drehrichtung von Dominostein zu gewährleisten
                int exeOrderInt = mt.nextInt(0, 3);
                switch (exeOrderInt) {

                    case 0:
                        if (x > 0 && !matrix[x - 1][y].isDominoSet() && !currentCell.isDominoSet()) {
                           if (currentCell.setDomino(matrix[x - 1][y])) {
                              if(matrix[x - 1][y].setDomino(currentCell)) {
                                  dominoCounter++;
                                  continue;
                              }
                           }
                        }
                        break;


                    case 1:
                        if (x < 9 && !matrix[x + 1][y].isDominoSet()) {
                            if(currentCell.setDomino(matrix[x + 1][y])) {
                                if(matrix[x + 1][y].setDomino(currentCell)) {
                                    dominoCounter++;
                                    continue;
                                }
                            }
                        }
                        break;

                    case 2:
                        if (y > 0 && !matrix[x][y - 1].isDominoSet()) {
                            if(currentCell.setDomino(matrix[x][y - 1])) {
                               if(matrix[x][y - 1].setDomino(currentCell)) {
                                   dominoCounter++;
                                   continue;
                               }
                            }
                        }
                        break;
                    case 3:
                        if (y < 9) {
                            if (matrix[x][y + 1].isDominoSet()) {
                                if(currentCell.setDomino(matrix[x][y + 1])) {
                                    if(matrix[x][y + 1].setDomino(currentCell)) {
                                        dominoCounter++;
                                        continue;
                                    }
                                }
                            }
                        }
                        break;

                }
            }
        }
    }

    public boolean isFitnessHigherCompare(Solution solution){
        if(solution.getFitness()>fitness){
            return true;
        }
        return false;
    }

}


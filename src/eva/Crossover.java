package eva;

import configuration.Configuration;
import configuration.MersenneTwister;

import java.util.ArrayList;

public class Crossover {

    private static MersenneTwister mt = new MersenneTwister();

    public ArrayList<Solution> Crossover(ArrayList<ArrayList<Solution>> pairedSolutionList){

        ArrayList<Solution> solutionsNewChildren = new ArrayList<>();

        for (int i = 0; i<pairedSolutionList.size();i++){

            //ADD KIDS
            solutionsNewChildren.addAll(doCrossover(pairedSolutionList.get(i).get(0),pairedSolutionList.get(i).get(1)));

        }

        return solutionsNewChildren;


    }


    private ArrayList<Solution> doCrossover(Solution parent1,Solution parent2){
        System.out.println(" CROSSOVER PARENTS :");
        System.out.println(parent1.getFitness());
        System.out.println(parent2.getFitness());


        int[][] chromosomParent1 = parent1.getChromaMatrix();
        int[][] chromosomParent2 = parent2.getChromaMatrix();
        int[][] chromosomChildren1 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];
        int[][] chromosomChildren2 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];

        int child1DominoCount = 0;
        int child2DominoCount = 0;
        int noSolutionCounter = 0;

        while ((noSolutionCounter<Configuration.CrossoverNoSolutionCounterMax) && (!(child1DominoCount==Configuration.AmountOfSections
                && child2DominoCount==Configuration.AmountOfSections))){

            noSolutionCounter++;

                int x = mt.nextInt(0,Configuration.GRID_SIZE);
                int y = mt.nextInt(0,Configuration.GRID_SIZE);

                boolean isP1ChromoGiverC1 = mt.nextBoolean();

                if(isP1ChromoGiverC1){

                    if(chromosomParent1[x][y]!=0) {
                        child1DominoCount+=tryDo1Crossover(x,y,chromosomParent1,chromosomChildren1,child1DominoCount);
                    }
                    if (chromosomParent2[x][y]!=0){
                        child2DominoCount+=tryDo1Crossover(x,y,chromosomParent2,chromosomChildren2,child2DominoCount);
                    }

                }
                else{

                    if(chromosomParent1[x][y]!=0) {
                        child2DominoCount+=tryDo1Crossover(x, y, chromosomParent1, chromosomChildren2,child2DominoCount);
                    }
                    if(chromosomParent2[x][y]!=0) {
                        child1DominoCount+=tryDo1Crossover(x, y, chromosomParent2, chromosomChildren1,child1DominoCount);
                    }


                }

        }


        ArrayList<Solution> children = new ArrayList<>();
        Solution child1 = new Solution();
        Solution child2 = new Solution();

        if(noSolutionCounter<Configuration.CrossoverNoSolutionCounterMax) {

             child1 = new Solution();
            child1.placeDominosByChromaMatrix(chromosomChildren1);

             child2 = new Solution();
            child2.placeDominosByChromaMatrix(chromosomChildren2);
        }

        children.add(child1);
        children.add(child2);

        System.out.println(child1.getFitness());
        System.out.println(child2.getFitness());
        return children;
    }


    private int tryDo1Crossover(int x, int y, int[][] chromosomParent1,int[][] chromosomChildren1, int child1DominoCount){

        int switchIndex = mt.nextInt(0,3);

        switch(switchIndex) {
            case 0:
            if (x > 0 && chromosomParent1[x][y] == chromosomParent1[x - 1][y]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x - 1][y] == 0) {
                if (child1DominoCount < Configuration.AmountOfSections) {


                    chromosomChildren1[x][y] = (1 + child1DominoCount);
                    chromosomChildren1[x - 1][y] = (1 + child1DominoCount);
                    return 1;
                }
            }
            break;

            case 1:
            if (x < (Configuration.GRID_SIZE - 1) && chromosomParent1[x][y] == chromosomParent1[x + 1][y]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x + 1][y] == 0) {
                if (child1DominoCount < Configuration.AmountOfSections) {

                    chromosomChildren1[x][y] = 1 + child1DominoCount;
                    chromosomChildren1[x + 1][y] = 1 + child1DominoCount;
                    return 1;
                }
            }
            break;

            case 2:
            if (y > 0 && chromosomParent1[x][y] == chromosomParent1[x][y - 1]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x][y - 1] == 0) {
                if (child1DominoCount < Configuration.AmountOfSections) {

                    chromosomChildren1[x][y] = 1 + child1DominoCount;
                    chromosomChildren1[x][y - 1] = 1 + child1DominoCount;
                    return 1;
                }
            }
            break;

            case 3:
            if (y < (Configuration.GRID_SIZE - 1) && chromosomParent1[x][y] == chromosomParent1[x][y + 1]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x][y + 1] == 0) {
                if (child1DominoCount < Configuration.AmountOfSections) {
                    chromosomChildren1[x][y] = 1 + child1DominoCount;
                    chromosomChildren1[x][y + 1] = 1 + child1DominoCount;
                    return 1;
                }

            }
            break;

        }
        return 0;
    }

}

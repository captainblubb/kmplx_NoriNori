package eva;

import configuration.Configuration;
import configuration.MersenneTwister;

import java.util.ArrayList;

public class Crossover {


    public ArrayList<Solution> Crossover(ArrayList<ArrayList<Solution>> pairedSolutionList){

        ArrayList<Solution> solutionsNewChildren = new ArrayList<>();

        for (int i = 0; i<pairedSolutionList.size();i++){

            //ADD KIDS
            solutionsNewChildren.addAll(doCrossover(pairedSolutionList.get(i).get(0),pairedSolutionList.get(i).get(1)));

        }

        return solutionsNewChildren;


    }


    public ArrayList<Solution> doCrossover(Solution parent1,Solution parent2){

        int[][] chromosomParent1 = parent1.getChromaMatrix();
        int[][] chromosomParent2 = parent2.getChromaMatrix();
        int[][] chromosomChildren1 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];
        int[][] chromosomChildren2 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];

        int child1DominoCount = 0;
        int child2DominoCount = 0;
        MersenneTwister mt = new MersenneTwister();

        while (!(child1DominoCount==Configuration.instance.AmountOfSections && child2DominoCount==Configuration.instance.AmountOfSections)){

                int x = mt.nextInt(0,Configuration.GRID_SIZE-1);
                int y = mt.nextInt(0,Configuration.GRID_SIZE-1);

                boolean isP1ChromoGiverC1 = mt.nextBoolean();

                if(isP1ChromoGiverC1){

                    if(chromosomParent1[x][y]!=0) {
                        tryDo1Crossover(x,y,chromosomParent1,chromosomChildren1,child1DominoCount);
                    }
                    if (chromosomParent2[x][y]!=0){
                        tryDo1Crossover(x,y,chromosomParent2,chromosomChildren2,child2DominoCount);
                    }
                }
                else{

                    if(chromosomParent1[x][y]!=0) {
                        tryDo1Crossover(x, y, chromosomParent1, chromosomChildren2, child2DominoCount);
                    }
                    if(chromosomParent2[x][y]!=0) {
                        tryDo1Crossover(x, y, chromosomParent2, chromosomChildren1, child1DominoCount);
                    }

                }

        }

        ArrayList<Solution> children = new ArrayList<>();
        Solution Child1 = new Solution();
        Child1.placeDominosByChromaMatrix(chromosomChildren1);

        Solution Child2 = new Solution();
        Child2.placeDominosByChromaMatrix(chromosomChildren2);

        return children;
    }


    public void tryDo1Crossover(int x, int y, int[][] chromosomParent1,int[][] chromosomChildren1, int child1DominoCount){

        if (x > 0 && chromosomParent1[x][y] == chromosomParent1[x-1][y]
                && chromosomChildren1[x][y]==0 && chromosomChildren1[x-1][y]==0)
        {
            child1DominoCount++;
            chromosomChildren1[x][y]=child1DominoCount;
            chromosomChildren1[x-1][y]=child1DominoCount;
        }
        else if(x < (Configuration.GRID_SIZE-1) && chromosomParent1[x][y] == chromosomParent1[x+1][y]
                && chromosomChildren1[x][y]==0 && chromosomChildren1[x+1][y]==0)
        {
            child1DominoCount++;
            chromosomChildren1[x][y]=child1DominoCount;
            chromosomChildren1[x+1][y]=child1DominoCount;
        }
        else if (y > 0 && chromosomParent1[x][y] == chromosomParent1[x][y-1]
                && chromosomChildren1[x][y]==0 && chromosomChildren1[x][y-1]==0)
        {
            child1DominoCount++;
            chromosomChildren1[x][y]=child1DominoCount;
            chromosomChildren1[x][y-1]=child1DominoCount;
        }
        else if(y < (Configuration.GRID_SIZE-1) && chromosomParent1[x][y] == chromosomParent1[x][y+1]
                && chromosomChildren1[x][y]==0 && chromosomChildren1[x][y+1]==0)
        {
            child1DominoCount++;
            chromosomChildren1[x][y]=child1DominoCount;
            chromosomChildren1[x][y+1]=child1DominoCount;
        }
    }

}

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

        int[][] ChromosomParent1 = parent1.getChromaMatrix();
        int[][] ChromosomParent2 = parent2.getChromaMatrix();
        int[][] ChromosomChildren = new int[][];

        int childDominoCount = 0;
        MersenneTwister mt = new MersenneTwister();
        while (childDominoCount==Configuration.instance.AmountOfSections){

                int x = mt.nextInt(0,Configuration.GRID_SIZE-1);
                int y = mt.nextInt(0,Configuration.GRID_SIZE-1);

                boolean isP1ChromoGiver = mt.nextBoolean();

                if(isP1ChromoGiver){



                }else {



                }



        }

    }
}

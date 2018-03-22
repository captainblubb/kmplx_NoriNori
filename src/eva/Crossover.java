package eva;

import base.MatrixCreator;
import configuration.Configuration;
import configuration.MersenneTwister;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Crossover {

    private static MersenneTwister mt = new MersenneTwister();

    public ArrayList<Solution> crossOverUniform(ArrayList<ArrayList<Solution>> pairedSolutionList){

        ArrayList<Solution> solutionsNewChildren = new ArrayList<>();

        for (int i = 0; i<pairedSolutionList.size();i++){

            //ADD KIDS
            solutionsNewChildren.addAll(doCrossoverUniform(pairedSolutionList.get(i).get(0),pairedSolutionList.get(i).get(1)));

        }
        return solutionsNewChildren;


    }


    private ArrayList<Solution> doCrossoverUniform(Solution parent1, Solution parent2){


        int[][] chromosomParent1 = parent1.getChromaMatrix();
        int[][] chromosomParent2 = parent2.getChromaMatrix();
        int[][] chromosomChildren1 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];
        int[][] chromosomChildren2 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];

        int child1DominoCount = 0;
        int child2DominoCount = 0;
        int noSolutionCounter = 0;

        int dominoCountC1 = mt.nextInt(0,(((Configuration.GRID_SIZE*Configuration.GRID_SIZE)/2) -5));
        int dominoCountC2 = mt.nextInt(0,(((Configuration.GRID_SIZE*Configuration.GRID_SIZE)/2) -5));

        while ((noSolutionCounter<Configuration.CrossoverNoSolutionCounterMax) && (!(child1DominoCount==dominoCountC1
                && child2DominoCount==dominoCountC2))){

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
        return children;
    }


    private int tryDo1Crossover(int x, int y, int[][] chromosomParent1,int[][] chromosomChildren1, int child1DominoCount){


            if (x > 0 && chromosomParent1[x][y] == chromosomParent1[x - 1][y]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x - 1][y] == 0) {

                    chromosomChildren1[x][y] = (1 + child1DominoCount);
                    chromosomChildren1[x - 1][y] = (1 + child1DominoCount);
                    return 1;

            }

            if (x < (Configuration.GRID_SIZE - 1) && chromosomParent1[x][y] == chromosomParent1[x + 1][y]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x + 1][y] == 0) {

                    chromosomChildren1[x][y] = 1 + child1DominoCount;
                    chromosomChildren1[x + 1][y] = 1 + child1DominoCount;
                    return 1;

            }

            if (y > 0 && chromosomParent1[x][y] == chromosomParent1[x][y - 1]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x][y - 1] == 0) {

                    chromosomChildren1[x][y] = 1 + child1DominoCount;
                    chromosomChildren1[x][y - 1] = 1 + child1DominoCount;
                    return 1;

            }

            if (y < (Configuration.GRID_SIZE - 1) && chromosomParent1[x][y] == chromosomParent1[x][y + 1]
                    && chromosomChildren1[x][y] == 0 && chromosomChildren1[x][y + 1] == 0) {

                    chromosomChildren1[x][y] = 1 + child1DominoCount;
                    chromosomChildren1[x][y + 1] = 1 + child1DominoCount;
                    return 1;


            }

        return 0;
    }


    public ArrayList<Solution> crossoverKCuts(ArrayList<ArrayList<Solution>> pairedSolutionList){
        ArrayList<Solution> solutionsNewChildren = new ArrayList<>();

        for (int i = 0; i<pairedSolutionList.size();i++){

            //ADD KIDS
            solutionsNewChildren.addAll(do1CrossoverkCuts(pairedSolutionList.get(i).get(0),pairedSolutionList.get(i).get(1)));

        }


        return solutionsNewChildren;
    }

    private ArrayList<Solution> do1CrossoverkCuts(Solution parent1, Solution parent2){
        ArrayList<Solution> children = new ArrayList<>();

        int[][] chromosomParent1 = parent1.getChromaMatrix();
        int[][] chromosomParent2 = parent2.getChromaMatrix();
        int[][] chromosomChildren1 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];
        int[][] chromosomChildren2 = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];

        //Generate Cuts
        int cutAmount = mt.nextInt(Configuration.CrossoverKCutsMinCuts,Configuration.CrossoverKCutsMaxCuts);
        ArrayList<Point> cuts = new ArrayList<>();
        cuts.add(new Point(0,0));
        for (int i = 0; i<cutAmount; i++){
            cuts.add(new Point(mt.nextInt(1,(Configuration.GRID_SIZE-2)),mt.nextInt(0,(Configuration.GRID_SIZE-1))));
        }
        cuts.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return pointComparer(o1,o2) ? 1:0;
            }
        });

        int child1DominoIndexCounter = 1;
        int child2DominoIndexCounter =0;
        for (int y =0; y<Configuration.GRID_SIZE;y++){
            for (int x =0; x<Configuration.GRID_SIZE;x++){
                for (int i=1;i<cuts.size();i++){
                    //Wenn chromosom an der Stelle = 0 und x und y zwischen dem jeweiligen cut
                    if(chromosomChildren1[x][y]==0
                            && ((i>0 && i<=(cuts.size()-1)
                            && pointComparer(new Point(x,y),cuts.get(i-1))
                            && pointComparer(cuts.get(i), new Point(x,y)))
                            || ((i+1)==cuts.size() && pointComparer(new Point(x,y),cuts.get(i))))){

                            if(cuts.size() % 2 == 1){
                                if(chromosomParent1[x][y]!=0) {
                                    child1DominoIndexCounter += tryDo1Crossover(x, y, chromosomParent1, chromosomChildren1, child1DominoIndexCounter);
                                }
                            }else {
                                if(chromosomParent2[x][y]!=0){
                                    child1DominoIndexCounter+= tryDo1Crossover(x,y,chromosomParent2,chromosomChildren1,child1DominoIndexCounter);
                                }

                            }
                    }
                    if(chromosomChildren2[x][y]==0
                            && ((i>0 && i<=(cuts.size()-1) && pointComparer(new Point(x,y),cuts.get(i-1)) && pointComparer(cuts.get(i), new Point(x,y)))
                            || ((i+1)==cuts.size() && pointComparer(new Point(x,y),cuts.get(i))))){

                        if((cuts.size()+1) % 2 == 1){
                            if(chromosomParent1[x][y]!=0) {
                                child2DominoIndexCounter += tryDo1Crossover(x, y, chromosomParent1, chromosomChildren2, child2DominoIndexCounter);
                            }
                        }else {
                            if(chromosomParent2[x][y]!=0){
                                child2DominoIndexCounter+= tryDo1Crossover(x,y,chromosomParent2,chromosomChildren2,child2DominoIndexCounter);
                            }

                        }
                    }

                }
            }
        }
        Solution child1 = new Solution();
        Solution child2 = new Solution();
        child1.placeDominosByChromaMatrix(chromosomChildren1);
        child2.placeDominosByChromaMatrix(chromosomChildren2);

        children.add(child1);
        children.add(child2);
        return children;
    }

    private boolean pointComparer(Point o1, Point o2){
        return (o1.y*Configuration.GRID_SIZE+o1.x)>=(o2.y*Configuration.GRID_SIZE+o2.x) ? true : false;
    }
}

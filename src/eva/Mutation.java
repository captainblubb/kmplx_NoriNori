package eva;

import configuration.Configuration;
import configuration.MersenneTwister;

import java.util.ArrayList;

public class Mutation {

    private int[][] replaceSingleDomino(int[][] parent1) {
        MersenneTwister mt = new MersenneTwister();

        int mutationRandom = mt.nextInt(0, Configuration.MutationChanceTotal);

        if (mutationRandom <= Configuration.MutationChance) {

            boolean isMutated = false;
            boolean isDominoToMutateFound = false;
            int counter =0;
            while (!isMutated) {
                int x = mt.nextInt(0, Configuration.GRID_SIZE);
                int y = mt.nextInt(0, Configuration.GRID_SIZE);
                int dominoIndex = 0;
                if(counter == 1000){
                    isMutated=true;
                }
                counter++;
                if (parent1[x][y] != 0) {

                    if (x > 0 && parent1[x - 1][y] == parent1[x][y]) {
                        dominoIndex = parent1[x][y];
                        isDominoToMutateFound = true;
                        parent1[x][y] = 0;
                        parent1[x - 1][y] = 0;

                    } else if (x < (Configuration.GRID_SIZE - 1) && parent1[x + 1][y] == parent1[x][y]) {
                        dominoIndex = parent1[x][y];
                        isDominoToMutateFound = true;
                        parent1[x][y] = 0;
                        parent1[x+1][y] = 0;

                    } else if (y > 0 && parent1[x][y - 1] == parent1[x][y]) {
                        dominoIndex = parent1[x][y];
                        isDominoToMutateFound = true;
                        parent1[x][y] = 0;
                        parent1[x][y - 1] = 0;

                    } else if (y < (Configuration.GRID_SIZE - 1) && parent1[x][y + 1] == parent1[x][y + 1]) {
                        dominoIndex = parent1[x][y];
                        isDominoToMutateFound = true;
                        parent1[x][y] = 0;
                        parent1[x][y + 1] = 0;
                    }

                }

                while (isDominoToMutateFound) {
                    int xx = mt.nextInt(0, Configuration.GRID_SIZE);
                    int yy = mt.nextInt(0, Configuration.GRID_SIZE);

                    if (parent1[xx][yy] == 0) {

                        if (xx > 0 && parent1[xx - 1][yy] == parent1[xx][yy]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx - 1][yy] = dominoIndex;
                            isDominoToMutateFound = false;
                            isMutated = true;

                        } else if (xx < (Configuration.GRID_SIZE - 1) && parent1[xx + 1][yy] == parent1[xx][yy]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx + 1][yy] = dominoIndex;
                            isDominoToMutateFound = false;
                            isMutated = true;

                        } else if (yy > 0 && parent1[xx][yy - 1] == parent1[xx][yy]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx][yy - 1] = dominoIndex;
                            isDominoToMutateFound = false;
                            isMutated = true;

                        } else if (yy < (Configuration.GRID_SIZE - 1) && parent1[xx][yy + 1] == parent1[xx][yy + 1]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx][yy + 1] = dominoIndex;
                            isDominoToMutateFound = false;
                            isMutated = true;
                        }
                    }
                }
            }
        }
        return parent1;
    }

    public ArrayList<Solution> mutatePopoluationSingleDominoReplaced(ArrayList<Solution> solutions) {
        ArrayList<Solution> newPopulation = new ArrayList<>();
        for (int i = 0; i < solutions.size(); i++) {
            Solution solution = new Solution();
            solution.placeDominosByChromaMatrix(solutions.get(i).getChromaMatrix());
            newPopulation.add(solution);
            newPopulation.get(i).placeDominosByChromaMatrix(replaceSingleDomino(solutions.get(i).getChromaMatrix()));
            newPopulation.get(i).calculateFitness();
        }
        return newPopulation;
    }

    public ArrayList<Solution> mutatePopolationPlaceNewDomino(ArrayList<Solution> solutions) {
        ArrayList<Solution> newPopulation = new ArrayList<>();
        for (int i = 0; i < solutions.size(); i++) {
            Solution solution = new Solution();
            solution.placeDominosByChromaMatrix(solutions.get(i).getChromaMatrix());
            newPopulation.add(solution);
            newPopulation.get(i).placeDominosByChromaMatrix(placeNewDominoByChroma(solutions.get(i).getChromaMatrix()));
            newPopulation.get(i).calculateFitness();
        }
        return newPopulation;
    }

    private int[][] placeNewDominoByChroma(int[][] parent1) {

        MersenneTwister mt = new MersenneTwister();

        int mutationRandom = mt.nextInt(0, Configuration.MutationChanceTotal);

        if (mutationRandom <= Configuration.MutationChance) {

            boolean isMutated = false;
            //Collect Indexes
            ArrayList<Integer> dominoIndexes = new ArrayList<>();
            for (int y = 0; y < Configuration.GRID_SIZE; y++) {
                for (int x = 0; x < Configuration.GRID_SIZE; x++) {
                    if (parent1[x][y] != 0) {
                        if (!dominoIndexes.contains(parent1[x][y])) {
                            dominoIndexes.add(parent1[x][y]);
                        }
                    }
                }
            }

            //Find new Index
            boolean newIndexFound = false;
            int index = 1;
            while (!newIndexFound) {
                if (!(dominoIndexes.contains(index))) {
                    newIndexFound = true;
                } else {
                    index++;
                }
            }
            int noNewPlacefoundCounter = 0;
            while (!isMutated) {
                int x = mt.nextInt(0, Configuration.GRID_SIZE);
                int y = mt.nextInt(0, Configuration.GRID_SIZE);

                noNewPlacefoundCounter++;

                if (noNewPlacefoundCounter > 1000) {
                    isMutated = true;
                }

                if (parent1[x][y] == 0) {

                    if (x > 0 && parent1[x - 1][y] == parent1[x][y] && parent1[x][y] == 0) {
                        parent1[x][y] = index;
                        parent1[x - 1][y] = index;
                        isMutated = true;

                    } else if (x < (Configuration.GRID_SIZE - 1) && parent1[x + 1][y] == parent1[x][y] && parent1[x][y] == 0) {
                        parent1[x][y] = index;
                        parent1[x + 1][y] = index;
                        isMutated = true;

                    } else if (y > 0 && parent1[x][y] == 0 && parent1[x][y - 1] == parent1[x][y]) {
                        parent1[x][y] = index;
                        parent1[x][y - 1] = index;
                        isMutated = true;

                    } else if (y < (Configuration.GRID_SIZE - 1) && parent1[x][y] == 0 && parent1[x][y + 1] == parent1[x][y + 1]) {
                        parent1[x][y] = index;
                        parent1[x][y + 1] = index;
                        isMutated = true;
                    }

                }

            }
        }
        return parent1;
    }

    public ArrayList<Solution> mutatePopulationRemoveDomino(ArrayList<Solution> solutions){
        ArrayList<Solution> newPopulation = new ArrayList<>();
        for (int i = 0; i < solutions.size(); i++) {
            Solution solution = new Solution();
            solution.placeDominosByChromaMatrix(solutions.get(i).getChromaMatrix());
            newPopulation.add(solution);
            newPopulation.get(i).placeDominosByChromaMatrix(removeSingleDomino(solutions.get(i).getChromaMatrix()));
            newPopulation.get(i).calculateFitness();
        }
        return newPopulation;
    }

    private int[][] removeSingleDomino(int[][] parent1) {
        MersenneTwister mt = new MersenneTwister();

        int mutationRandom = mt.nextInt(0, Configuration.MutationChanceTotal);

        if (mutationRandom <= Configuration.MutationChance) {
        boolean isMutated = false;
        int counter =0;
        while (!isMutated) {
            int x = mt.nextInt(0, Configuration.GRID_SIZE);
            int y = mt.nextInt(0, Configuration.GRID_SIZE);
            if (counter== 1000){
                isMutated = true;
            }
            counter++;
            if (parent1[x][y] != 0) {

                if (x > 0 && parent1[x - 1][y] == parent1[x][y]) {
                    isMutated = true;
                    parent1[x][y] = 0;
                    parent1[x - 1][y] = 0;

                } else if (x < (Configuration.GRID_SIZE - 1) && parent1[x + 1][y] == parent1[x][y]) {
                    isMutated = true;
                    parent1[x][y] = 0;
                    parent1[x + 1][y] = 0;

                } else if (y > 0 && parent1[x][y - 1] == parent1[x][y]) {
                    isMutated = true;
                    parent1[x][y] = 0;
                    parent1[x][y - 1] = 0;

                } else if (y < (Configuration.GRID_SIZE - 1) && parent1[x][y + 1] == parent1[x][y + 1]) {
                    isMutated = true;
                    parent1[x][y] = 0;
                    parent1[x][y + 1] = 0;
                }

            }
            }
        }
        return parent1;
    }

}
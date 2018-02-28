package eva;

import configuration.Configuration;
import configuration.MersenneTwister;

public class Mutation {

    public int[][] placeSingleDominoNew(int[][] parent1){
        MersenneTwister mt = new MersenneTwister();

        int mutationRandom = mt.nextInt(0, Configuration.MutationChanceTotal);

        if(mutationRandom<=Configuration.MutationChance);{

            boolean isMutated = false;
            while(!isMutated){

                boolean isDominoToMutateFound = false;
                int x = mt.nextInt(0,Configuration.GRID_SIZE);
                int y = mt.nextInt(0,Configuration.GRID_SIZE);
                int dominoIndex =0;

                if(parent1[x][y] != 0) {

                    if (x > 0 && parent1[x - 1][y] == parent1[x][y]) {
                        dominoIndex=parent1[x][y];
                        isDominoToMutateFound=true;
                        parent1[x][y] = 0;
                        parent1[x - 1][y] = 0;

                    } else if (x < (Configuration.GRID_SIZE - 1) && parent1[x + 1][y] == parent1[x][y]) {
                        dominoIndex=parent1[x][y];
                        isDominoToMutateFound=true;
                        parent1[x][y] = 0;
                        parent1[x - 1][y] = 0;

                    } else if (y > 0 && parent1[x][y - 1] == parent1[x][y]) {
                        dominoIndex=parent1[x][y];
                        isDominoToMutateFound=true;
                        parent1[x][y] = 0;
                        parent1[x - 1][y] = 0;

                    } else if (y < (Configuration.GRID_SIZE - 1) && parent1[x][y + 1] == parent1[x][y + 1]) {
                        dominoIndex=parent1[x][y];
                        isDominoToMutateFound=true;
                        parent1[x][y] = 0;
                        parent1[x - 1][y] = 0;
                    }

                }

                while (isDominoToMutateFound){
                    int xx = mt.nextInt(0,Configuration.GRID_SIZE);
                    int yy = mt.nextInt(0,Configuration.GRID_SIZE);

                    if(parent1[xx][yy] == 0) {

                        if (xx > 0 && parent1[xx - 1][yy] == parent1[xx][yy]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx - 1][yy] = dominoIndex;
                            isDominoToMutateFound=true;
                            isMutated=true;

                        } else if (xx < (Configuration.GRID_SIZE - 1) && parent1[xx + 1][yy] == parent1[x][yy]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx- 1][yy] = dominoIndex;
                            isDominoToMutateFound=true;
                            isMutated=true;

                        } else if (yy > 0 && parent1[xx][yy - 1] == parent1[xx][yy]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx - 1][yy] = dominoIndex;
                            isDominoToMutateFound=true;
                            isMutated=true;

                        } else if (yy < (Configuration.GRID_SIZE - 1) && parent1[xx][yy + 1] == parent1[xx][yy + 1]) {
                            parent1[xx][yy] = dominoIndex;
                            parent1[xx - 1][yy] = dominoIndex;
                            isDominoToMutateFound=true;
                            isMutated=true;
                        }
                    }
                }
            }
        }
        return parent1;
    }
}

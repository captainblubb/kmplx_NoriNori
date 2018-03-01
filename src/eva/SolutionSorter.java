package eva;

import java.util.ArrayList;

public class SolutionSorter {


    public static ArrayList<Solution> sortSolutionsBubble(ArrayList<Solution> solutions){

        for (int k =0; k<solutions.size();k++) {
            for (int i = (solutions.size() - 2); i >= 0; i--) {
                if (solutions.get(i).isFitnessHigherCompare(solutions.get(i + 1))) {
                    Solution swap = solutions.remove(i);
                    solutions.add(i + 1, swap);
                }
            }
        }

        return solutions;
    }
}

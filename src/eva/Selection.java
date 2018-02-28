package eva;

import configuration.Configuration;

import java.util.*;

public class Selection {


    public ArrayList<ArrayList<Solution>> Selection( ArrayList<Solution> solutions){


        //Fitness aktualisieren
        for(int i = 0; i<solutions.size();i++){
            solutions.get(i).calculateFitness();
        }

        //Sortieren
        for (int k =0; k<(solutions.size()/2+1);k++) {
            for (int i = solutions.size() - 1; i >= 0; i++) {
                if (!solutions.get(i).isFitnessHigherCompare(solutions.get(i + 1))) {
                    Solution swap = solutions.remove(i);
                    solutions.add(i + 1, swap);
                }
            }
        }

        //SELEKTION
        int countOfPairsToSelect =Configuration.Selection_CountOfSelections;
        if (solutions.size()<countOfPairsToSelect){
            if (solutions.size()>1) {
                countOfPairsToSelect = solutions.size() % 2 == 0 ? solutions.size() : solutions.size() - 1;
            }
        }

        ArrayList<ArrayList<Solution>> pairedSolutionList = new ArrayList<ArrayList<Solution>>();
        for(int i = 0; i< countOfPairsToSelect;i+=2){
            ArrayList<Solution> pair = new ArrayList<>();
            pair.add(solutions.get(i));
            pair.add(solutions.get(i+1));
            pairedSolutionList.add(pair);
        }

        return pairedSolutionList;
    }


}

package eva;

import configuration.Configuration;

import java.util.ArrayList;

public class Turnament {

    public void rankTournament(ArrayList<Solution> solutions){

        //Fitness aktualisieren
        for(int i = 0; i<solutions.size();i++){
            solutions.get(i).calculateFitness();
        }

        //Sortieren
        solutions= SolutionSorter.sortSolutionsBubble(solutions);

        //SELEKTION
        int countOfPairsToSelect = Configuration.Turnament_Survivers;
        if (solutions.size()<countOfPairsToSelect){
            if (solutions.size()>1) {
                countOfPairsToSelect = solutions.size();
            }
        }

        ArrayList<ArrayList<Solution>> Survivors = new ArrayList<ArrayList<Solution>>();
        for(int i = 0; i< countOfPairsToSelect;i+=2){

        }
    }
}

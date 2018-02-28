package eva;

import configuration.Configuration;

import java.util.ArrayList;

public class Turnament {

    public ArrayList<Solution> rankTournament(ArrayList<Solution> solutions){
        System.out.println("Turnament");
        //Fitness aktualisieren
        for(int i = 0; i<solutions.size();i++){
            solutions.get(i).calculateFitness();
        }

        //Sortieren
        solutions= SolutionSorter.sortSolutionsBubble(solutions);

        //SELEKTION
        int countOfTurnamentSurvivors = Configuration.Turnament_Survivers;
        if (solutions.size()<countOfTurnamentSurvivors){
            if (solutions.size()>1) {
                countOfTurnamentSurvivors = solutions.size();
            }
        }

        ArrayList<Solution> survivors = new ArrayList<Solution>();
        for(int i = 0; i< countOfTurnamentSurvivors;i++){
            survivors.add(solutions.get(i));
        }

        return survivors;
    }
}

package eva;

import configuration.Configuration;
import configuration.MersenneTwister;

import java.util.ArrayList;

public class Turnament {

    public ArrayList<Solution> rankTournament(ArrayList<Solution> solutions){

        MersenneTwister mt = new MersenneTwister();

        int sumOfFitness =0;
        //Fitness aktualisieren
        for(int i = 0; i<solutions.size();i++){
            solutions.get(i).calculateFitness();
            sumOfFitness+=solutions.get(i).getFitness();
        }

        //Sortieren
        solutions= SolutionSorter.sortSolutionsBubble(solutions);

        //SELEKTION
        int countOfTurnamentSurvivors = Configuration.Turnament_Survivers;
        if (solutions.size()<countOfTurnamentSurvivors){
                countOfTurnamentSurvivors = solutions.size();
        }


        ArrayList<Solution> survivors = new ArrayList<Solution>();

        while (survivors.size() < countOfTurnamentSurvivors) {
            for (int i = 0; i < (solutions.size() - 1); i++) {
                if (solutions.get(i).getFitness() * 5 >= mt.nextInt(0, sumOfFitness)) {
                    survivors.add(solutions.get(i));
                }
            }
        }


        return survivors;
    }
}

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

        for (int i = 0; i<solutions.size();i++){
            System.out.print(" "+solutions.get(i).getFitness());
        }
        System.out.println();

        ArrayList<Solution> survivors = new ArrayList<Solution>();
        survivors.add(solutions.get(0));
        survivors.add(solutions.get(1));
        survivors.add(solutions.get(2));
        for(int i = 2; i< countOfTurnamentSurvivors;i++){
            survivors.add(solutions.get(i));
        }

        for (int i = 0; i<survivors.size();i++){
            System.out.print(" "+survivors.get(i).getFitness());
        }
        System.out.println();

        return survivors;
    }
}

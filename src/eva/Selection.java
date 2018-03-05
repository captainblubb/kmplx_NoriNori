package eva;

import configuration.Configuration;
import configuration.MersenneTwister;

import java.util.*;

public class Selection {


    public ArrayList<ArrayList<Solution>> selectionOfTheBest(ArrayList<Solution> solutions){


        solutions = updateFitnessAndSortList(solutions);

        ArrayList<Solution> nextGeneration = new ArrayList<>();
        for(int i = 0; i< calcCountOfPairsToSelect(solutions) && i<solutions.size()-1;i+=2){
           nextGeneration.add(solutions.get(i));
        }

        Collections.shuffle(nextGeneration);

        ArrayList<ArrayList<Solution>> pairedSolutionList = new ArrayList<ArrayList<Solution>>();

        for(int i = 0; i< nextGeneration.size()-1;i+=2){
            ArrayList<Solution> pair = new ArrayList<>();
            pair.add(nextGeneration.get(i));
            pair.add(nextGeneration.get(i+1));
            pairedSolutionList.add(pair);
            }

        return pairedSolutionList;
    }


    public ArrayList<ArrayList<Solution>> selectBestWithWorst(ArrayList<Solution> solutions){

        ArrayList<ArrayList<Solution>> pairedSolutionList = new ArrayList<ArrayList<Solution>>();

        for(int i = 0; i< calcCountOfPairsToSelect(solutions) && i<(pairedSolutionList.size()-2);i++){
            ArrayList<Solution> pair = new ArrayList<>();
            pair.add(solutions.get(i));
            pair.add(solutions.get(calcCountOfPairsToSelect(solutions)-1-i));
            pairedSolutionList.add(pair);
        }

        return pairedSolutionList;
    }


    public ArrayList<Solution> updateFitnessAndSortList(ArrayList<Solution> solutions){

        //Fitness aktualisieren
        for(int i = 0; i<solutions.size();i++){
            solutions.get(i).calculateFitness();
        }

        solutions= SolutionSorter.sortSolutionsBubble(solutions);

        return solutions;
    }

    public int calcCountOfPairsToSelect(ArrayList<Solution> solutions){
        //SELEKTION
        int countOfPairsToSelect =Configuration.Selection_CountOfSelections;
        if (solutions.size()<countOfPairsToSelect){
            if (solutions.size()>1) {
                countOfPairsToSelect = solutions.size() % 2 == 0 ? solutions.size() : solutions.size() - 1;
            }
        }
        return countOfPairsToSelect;
    }

    public ArrayList<ArrayList<Solution>> selectionOfTheBestWithProbability(ArrayList<Solution> solutions){


        MersenneTwister mt = new MersenneTwister();

        int sumOfFitness =0;

        //Fitness aktualisieren
        for(int i = 0; i<solutions.size();i++){
            solutions.get(i).calculateFitness();
            sumOfFitness+=solutions.get(i).getFitness();
        }

        ArrayList<Solution> orderdForpairedWithFitnessProbability = new ArrayList<>();

        while (orderdForpairedWithFitnessProbability.size()<= Configuration.Selection_CountOfSelections) {
            for (int i = 0; i < solutions.size(); i++) {
                if (solutions.get(i).getFitness() >= mt.nextInt(0, sumOfFitness)) {
                    orderdForpairedWithFitnessProbability.add(solutions.get(i));
                }
            }
        }



        ArrayList<ArrayList<Solution>> pairedSolutionList = new ArrayList<ArrayList<Solution>>();

        for(int i = 0; i< orderdForpairedWithFitnessProbability.size()-2;i+=2){
            ArrayList<Solution> pair = new ArrayList<>();
            pair.add(orderdForpairedWithFitnessProbability.get(i));
            pair.add(orderdForpairedWithFitnessProbability.get(i+1));
            pairedSolutionList.add(pair);
        }

        return pairedSolutionList;
    }
}

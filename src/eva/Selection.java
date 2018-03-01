package eva;

import configuration.Configuration;

import java.util.*;

public class Selection {


    public ArrayList<ArrayList<Solution>> selectionOfTheBest(ArrayList<Solution> solutions){
        System.out.println("start selectionOfTheBest");

        solutions = updateFitnessAndSortList(solutions);

        ArrayList<ArrayList<Solution>> pairedSolutionList = new ArrayList<ArrayList<Solution>>();

        for(int i = 0; i< calcCountOfPairsToSelect(solutions);i+=2){
            ArrayList<Solution> pair = new ArrayList<>();
            pair.add(solutions.get(i));
            pair.add(solutions.get(i+1));
            pairedSolutionList.add(pair);
        }

        return pairedSolutionList;
    }


    public ArrayList<ArrayList<Solution>> selectBestWithWorst(ArrayList<Solution> solutions){
        System.out.println("start selectBestWithWorst");

        ArrayList<ArrayList<Solution>> pairedSolutionList = new ArrayList<ArrayList<Solution>>();

        for(int i = 0; i< calcCountOfPairsToSelect(solutions);i++){
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


}

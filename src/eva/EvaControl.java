package eva;

import base.Cell;
import base.MatrixCreator;
import configuration.Configuration;
import gui.Controller;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Collections;

public class EvaControl implements Runnable{

   private Crossover crossover;
   private Mutation mutation;
   private Turnament turnament;
   private Selection selection;
   private Cell[][] matrix;
   private int highestFiness;
   private Controller controller;
   private ArrayList<Solution> population;
   private Thread evaThread;
   private int counter = 0;


    public EvaControl(Controller controller){
        this.controller = controller;
        crossover = new Crossover();
        mutation= new Mutation();
        turnament = new Turnament();
        selection = new Selection();
        this.population = initPopulation();

        /*Solution solution = new Solution();
        controller.setMatrixToGridCells(solution.getMatrix());
        solution.calculateFitness();
        System.out.println(solution.getFitness());
        System.out.println("done");
        solution.calculateChromosom();
        System.out.println(solution.chromosomToString());
        int[][] TestClearBySetMatrix = new int[Configuration.GRID_SIZE][Configuration.GRID_SIZE];
        TestClearBySetMatrix[0][0] = 1;
        TestClearBySetMatrix[0][1] = 1;
        solution.placeDominosByChromaMatrix(TestClearBySetMatrix);
        solution.calculateFitness();
        System.out.println(solution.getFitness());
        */
    }




    @Override
    public void run(){
        try{
            evaThread = Thread.currentThread();
            while (!Thread.currentThread().isInterrupted()) {
                Platform.runLater(() -> {


                        for (int i = 0; i < population.size(); i++) {
                            if (population.get(i).getIsSolved()) {
                                controller.setMatrixToGridCells(population.get(i).getMatrix());
                                stopEvaThread();
                            }
                        }
                        // Selektieren -> kreuzen
                        if(counter == 0) {
                            this.population.addAll(crossover.crossoverKCuts(selection.selectionOfTheBestWithProbability(this.population)));
                            counter =1;
                        }else if(counter == 1)
                        {
                            this.population.addAll(crossover.crossOverUniform(selection.selectionOfTheBestWithProbability(this.population)));
                            counter=2;
                        }else if(counter ==2){
                            this.population.addAll(crossover.crossOverUniform(selection.selectionOfTheBestWithProbability(this.population)));
                            counter=3;
                        }else if(counter==3){
                            this.population.addAll(crossover.crossOverUniform(selection.selectionOfTheBestWithProbability(this.population)));
                            counter=0;
                        }

                        mutation.mutatePopolationPlaceNewDomino(population);
                        mutation.mutatePopoluationSingleDominoReplaced(population);
                        mutation.mutatePopulationRemoveDomino(population);
                        population = turnament.rankTournament(population);
                        updateControllerGridWithBestFitnessSolution();
                        controller.incGenerationCounter();


                });
                Thread.sleep(Configuration.Threadsleeptime);
            }
         }catch (InterruptedException e) {
            System.out.println(e);
            Thread.currentThread().interrupt();
            stopEvaThread();
        }
    }





    private void stopEvaThread() {
        evaThread.interrupt();
        controller.stopSimulation();
    }

    private void updateControllerGridWithBestFitnessSolution(){
        int bestFitness=0;
        int bestFitnessIndex = -1;
        ArrayList<Integer> fitnessList = new ArrayList<>();
        for(int i = 0; i<population.size();i++){
           if(population.get(i).getFitness()>bestFitness){
               bestFitness = population.get(i).getFitness();
               bestFitnessIndex=i;

           }
            fitnessList.add(population.get(i).getFitness());
        }
        Collections.sort(fitnessList,Collections.reverseOrder());
        System.out.println(fitnessList);
        if(bestFitnessIndex>=0) {
            System.out.println(population.get(bestFitnessIndex).getFitness());
            if (population.get(bestFitnessIndex).getFitness()> controller.getHighestFitness()) {
                controller.setMatrixToGridCells(population.get(bestFitnessIndex).getMatrix());
                controller.updateHighestFitness(population.get(bestFitnessIndex).getFitness());
            }
        }

    }

    private ArrayList<Solution> initPopulation(){

        ArrayList<Solution> newPopoluation = new ArrayList<>();

        for (int i = 0; i<Configuration.initialPopolution;i++){
            Solution solution= new Solution();
            newPopoluation.add(solution);
        }

        return newPopoluation;
    }

    private int getHighestFitness(){
        int bestFitness=0;
        for(int i = 0; i<population.size();i++){
            bestFitness = bestFitness<population.get(i).getFitness() ? population.get(i).getFitness() : bestFitness;
        }
        return bestFitness;
    }

    /*
        Solution (Matrix -> Chromosom)
        -> Selektion => Fitness durch so wenig Falsche Dominos wie möglich
        -> Mutation => random Domino posi tauschen
        -> crossOverUniform => Uniform?
        -> Turnament => % Chance durch fitness
             -> Matrix anzeigen
                    ->NEXT GENERATION
         */

}

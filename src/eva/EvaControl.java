package eva;

import base.Cell;
import configuration.Configuration;
import gui.Controller;
import javafx.application.Platform;

import java.util.ArrayList;

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



                       System.out.println("Start EVA");

                        for (int i = 0; i < population.size(); i++) {
                            if (population.get(i).getIsSolved()) {
                                controller.setMatrixToGridCells(population.get(i).getMatrix());
                                System.out.println("SOLVED");
                                stopEvaThread();
                            }
                        }


                        System.out.println("Popoluation stand " + population.size());
                        //Selektieren -> kreuzen
                        this.population.addAll(crossover.Crossover(selection.selectionOfTheBest(this.population)));
                        mutation.mutatePopoluationSingleDominoNew(population);
                        population = turnament.rankTournament(population);
                        updateControllerGridWithBestFitnessSolution();
                        controller.incGenerationCounter();

                });Thread.sleep(Configuration.Threadsleeptime);
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
        int bestFitnessIndex =0;
        for(int i = 0; i<population.size();i++){
           if(population.get(i).getFitness()>bestFitness){
               bestFitness = population.get(i).getFitness();
               bestFitnessIndex=i;
           }
        }
        System.out.println("#########################################");
        System.out.println("BEST FITNES:::"+bestFitness);
        System.out.println("#########################################");
        controller.setMatrixToGridCells(population.get(bestFitnessIndex).getMatrix());
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
        -> Crossover => Uniform?
        -> Turnament => % Chance durch fitness
             -> Matrix anzeigen
                    ->NEXT GENERATION
         */

}

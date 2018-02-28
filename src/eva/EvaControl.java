package eva;

import base.Cell;
import base.MatrixCreator;
import configuration.Configuration;
import gui.Controller;
import javafx.fxml.FXML;
import task.TaskData;

import java.awt.*;
import java.util.ArrayList;

public class EvaControl {

    Crossover crossover;
    Mutation mutation;
    Turnament turnament;
    Selection selection;
    Cell[][] matrix;
    ArrayList<ArrayList<Point>> taskData;
    Controller controller;


    public EvaControl(Controller controller){
        this.controller = controller;
        Solution solution = new Solution();
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

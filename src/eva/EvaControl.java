package eva;

import base.Cell;
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

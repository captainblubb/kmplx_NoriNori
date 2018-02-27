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


    public EvaControl(Controller controller){



    }

}

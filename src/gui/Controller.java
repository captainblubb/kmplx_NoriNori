package gui;


import base.Cell;
import eva.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static configuration.Configuration.GRID_SIZE;
import static configuration.Configuration.CELL_SIZE;

public class Controller implements IController {

    private int counter = 0;
    private base.Cell[][] matrix = new base.Cell[GRID_SIZE][GRID_SIZE];
    private EvaControl evaControl;

    @FXML
    private GridPane grid;

    @FXML
    private Label stepCounter;

    @FXML
    private Button stopButton;

    @FXML
    private Button startButton;

    @FXML
    private Button clearButton;

    public void initialize() {
        initGridConstraints();
        initGridCells();

    }



    private void initGridConstraints() {
        for (int i = 0; i < GRID_SIZE; i++) {
            ColumnConstraints column = new ColumnConstraints(CELL_SIZE);
            grid.getColumnConstraints().add(column);

            RowConstraints row = new RowConstraints(CELL_SIZE);
            grid.getRowConstraints().add(row);
        }
    }

    private void initGridCells() {
        grid.getChildren().clear();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                matrix[i][j] = new base.Cell(i, j);
                grid.add(matrix[i][j], i, j);
            }
        }
    }

    @FXML
    private void startSimulation() {
        startButton.disableProperty().setValue(true);
        clearButton.disableProperty().setValue(true);

        EvaControl evaControl = new EvaControl(this);


    }

    @FXML
    public void stopSimulation() {
        //  antThread.interrupt();
       // stopButton.disableProperty().setValue(true);
       // heatMapButton.disableProperty().setValue(false);
        clearButton.disableProperty().setValue(false);
    }



    public base.Cell getCell(int rowPos, int colPos) {
        return matrix[colPos][rowPos];
    }

    public void incStepCounter() {
        counter++;
    }

    public void updateCounterLabel() {
        stepCounter.setText("Steps: " + counter);
    }

    public GridPane getGrid() { return grid; }

    @FXML
    private void clearSimulation() {
        counter = 0;
        stepCounter.setText("Steps: " + counter);
        matrix = new Cell[GRID_SIZE][GRID_SIZE];
        initGridCells();
        startButton.disableProperty().setValue(false);

    }

    public Cell[][] getMatrix() {
        return matrix;
    }
}

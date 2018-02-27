package gui;


import base.Cell;
import base.MatrixCreator;
import eva.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import task.TaskData;

import static configuration.Configuration.GRID_SIZE;
import static configuration.Configuration.CELL_SIZE;

public class Controller implements IController {

    private int generation = 0;
    private base.Cell[][] matrix = new base.Cell[GRID_SIZE][GRID_SIZE];
    private EvaControl evaControl;

    @FXML
    private GridPane grid;

    @FXML
    private Label generationCounter;

    @FXML
    private Button stopButton;

    @FXML
    private Button startButton;

    @FXML
    private Button clearButton;

    public void initialize() {
        initGridConstraints();
        matrix = MatrixCreator.createMatrixFromTask();
        setMatrixToGridCells(matrix);

    }

    private void initGridConstraints() {
        for (int i = 0; i < GRID_SIZE; i++) {
            ColumnConstraints column = new ColumnConstraints(CELL_SIZE);
            grid.getColumnConstraints().add(column);

            RowConstraints row = new RowConstraints(CELL_SIZE);
            grid.getRowConstraints().add(row);
        }
    }

    private void setMatrixToGridCells(Cell[][] matrixInit) {
        grid.getChildren().clear();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid.add(matrixInit[i][j], i, j);
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

    public void incGenerationCounter() {
        generation++;
    }

    public void updateGenerationLabel() {
        generationCounter.setText("Steps: " + generation);
    }

    public GridPane getGrid() { return grid; }

    @FXML
    private void clearSimulation() {
        generation = 0;
        generationCounter.setText("Steps: " + generation);
        matrix = new Cell[GRID_SIZE][GRID_SIZE];
        setMatrixToGridCells(MatrixCreator.createMatrixFromTask());
        startButton.disableProperty().setValue(false);
    }

    public Cell[][] getMatrix() {
        return matrix;
    }
}

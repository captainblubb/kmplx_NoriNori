package gui;


import Backtracking.BacktrackingManager;
import Backtracking.CellBT;
import base.Cell;
import base.MatrixCreator;
import eva.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static configuration.Configuration.GRID_SIZE;
import static configuration.Configuration.CELL_SIZE;

public class Controller implements IController {

    private int generation = 0;
    private Cell[][] matrix = new base.Cell[GRID_SIZE][GRID_SIZE];
    private Thread evaThread;
    private EvaControl evaControl;
    private int highestFitness=0;

    @FXML
    private GridPane grid;

    @FXML
    private Label highestFitnessLabel;

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
        matrix = MatrixCreator.createEvaMatrixFromTask();
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

    public void setMatrixToGridCells(Cell[][] matrixInit) {
        grid.getChildren().clear();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid.add(matrixInit[i][j], i, j);
            }
        }
    }

    public void setMatrixToGridCellsBT(CellBT[][] matrixInit) {
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

       // BacktrackingManager btm = new BacktrackingManager(this);
        EvaControl evaControl = new EvaControl(this);
        evaThread = new Thread(evaControl);
        evaThread.start();

    }

    @FXML
    public void stopSimulation() {
        evaThread.interrupt();
        stopButton.disableProperty().setValue(true);
        clearButton.disableProperty().setValue(false);
    }

    public base.Cell getCell(int rowPos, int colPos) {
        return matrix[colPos][rowPos];
    }

    public void incGenerationCounter() {
        this.generation++;
        updateGenerationLabel();
    }

    public void updateHighestFitness(int fitness){
        if(highestFitness<fitness){
            highestFitness=fitness;
            highestFitnessLabel.setText("Highest Fitness "+fitness);
        }
    }

    public void updateGenerationLabel() {
        generationCounter.setText("Generation: " + generation);
    }

    public GridPane getGrid() { return grid; }

    @FXML
    private void clearSimulation() {
        generation = 0;
        generationCounter.setText("Generation: " + generation);
        matrix = new Cell[GRID_SIZE][GRID_SIZE];
        setMatrixToGridCells(MatrixCreator.createEvaMatrixFromTask());
        startButton.disableProperty().setValue(false);
    }

    public int getHighestFitness(){
        return highestFitness;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }
}

package base;


import configuration.Configuration;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static configuration.Configuration.GRID_SIZE;
import static configuration.Configuration.CELL_SIZE;

public class Controller {

    private int counter = 0;
    private int currentSpeed = 1000;
    private Cell[][] matrix = new Cell[GRID_SIZE][GRID_SIZE];

    @FXML
    private GridPane grid;

    @FXML
    private Label stepCounter;

    @FXML
    private Button startButton;

    @FXML
    private Button clearButton;

    public void initialize() {
        initGridConstraints();
        initGridCells();
        repaintGridLines();
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
                matrix[i][j] = new Cell(i, j);
                grid.add(matrix[i][j], i, j);
            }
        }
    }

    @FXML
    private void startSimulation() {

        Cell cell =matrix[5][5];
        cell.drawBorder(11, 11, 1, 1);

        startButton.disableProperty().setValue(true);
        clearButton.disableProperty().setValue(true);


    }

    @FXML
    public void stopSimulation() {
        //  antThread.interrupt();
       // stopButton.disableProperty().setValue(true);
       // heatMapButton.disableProperty().setValue(false);
        clearButton.disableProperty().setValue(false);
    }



    public void repaintGridLines() {
      /*  grid.setGridLinesVisible(false);
        grid.setGridLinesVisible(true); */
    }

    public Cell getCell(int rowPos, int colPos) {
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
        updateArrowDirection();
        repaintGridLines();
        startButton.disableProperty().setValue(false);

    }

    @FXML
    private void updateArrowDirection() {
        Platform.runLater(() -> {

        });
    }

}

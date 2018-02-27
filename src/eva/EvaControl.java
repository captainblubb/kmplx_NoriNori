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

        matrix = controller.getMatrix();
        taskData = TaskData.getSectionsForTask();
        setMatrixWithTaskData(taskData);
    }


    private void setMatrixWithTaskData(ArrayList<ArrayList<Point>> pointArray){

        for(int i = 0; i<pointArray.size(); i++){

            ArrayList<Cell> cellsThisSection = new ArrayList<>();
            for(int l = 0 ; l<pointArray.get(i).size();l++){
                    cellsThisSection.add(matrix[pointArray.get(i).get(l).x][pointArray.get(i).get(l).y]);
            }

            for(int k = 0; k<cellsThisSection.size();k++){
                Cell currentCell = cellsThisSection.get(k);

                //Section
                currentCell.setBelongsToSectionIndex(i);

                //Gehört die Zelle links NICHT zu der Section ?
                if(cellsThisSection.get(k).getRowPos()>0){
                    if(!(cellsThisSection.contains(matrix[currentCell.getRowPos()-1][currentCell.getColPos()]))){
                        currentCell.drawBorderLeft(Configuration.BORDER_THINKNESS);
                    }
                }else{
                    currentCell.drawBorderLeft(Configuration.BORDER_THINKNESS);
                }

                //Gehört die Zelle rechts NICHT zu der Section ?
                if(cellsThisSection.get(k).getRowPos()<9){
                    if(!(cellsThisSection.contains(matrix[currentCell.getRowPos()+1][currentCell.getColPos()]))){
                        currentCell.drawBorderRight(Configuration.BORDER_THINKNESS);
                    }
                }else {
                    currentCell.drawBorderRight(Configuration.BORDER_THINKNESS);
                }

                //Gehört die Zelle oben NICHT zu der Section ?
                if(cellsThisSection.get(k).getColPos()>0){
                    if(!(cellsThisSection.contains(matrix[currentCell.getRowPos()][currentCell.getColPos()-1]))){
                        currentCell.drawBorderTop(Configuration.BORDER_THINKNESS);
                    }
                }else {
                    currentCell.drawBorderTop(Configuration.BORDER_THINKNESS);
                }

                //Gehört die Zelle unten NICHT zu der Section ?
                if(cellsThisSection.get(k).getColPos()<9){
                    if(!(cellsThisSection.contains(matrix[currentCell.getRowPos()][currentCell.getColPos()+1]))){
                        currentCell.drawBorderBottom(Configuration.BORDER_THINKNESS);
                    }
                }else{
                    currentCell.drawBorderBottom(Configuration.BORDER_THINKNESS);
                }
            }

        }

    }


}

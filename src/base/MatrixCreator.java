package base;

import configuration.Configuration;
import eva.Selection;
import task.TaskData;

import java.awt.*;
import java.util.ArrayList;

import static configuration.Configuration.GRID_SIZE;

public class MatrixCreator {

    static Cell[][] matrix;
    static ArrayList<Section> sections;


    public static Cell[][] createMatrixFromTask(){

        matrix = new base.Cell[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                matrix[i][j] = new base.Cell(i, j);

            }
        }
        setMatrixWithTaskData(TaskData.getSectionsForTask());

        return matrix;
    }

    public static ArrayList<Section> getSectionsOfLastMatrix() {
        return sections;
    }

    private static void setMatrixWithTaskData(ArrayList<ArrayList<Point>> pointArray){

        sections = new ArrayList<>();
        for(int i = 0; i<pointArray.size(); i++){

            Section section = new Section();
            sections.add(section);
            ArrayList<Cell> cellsThisSection = new ArrayList<>();
            for(int l = 0 ; l<pointArray.get(i).size();l++){
                cellsThisSection.add(matrix[pointArray.get(i).get(l).x][pointArray.get(i).get(l).y]);
            }

            for(int k = 0; k<cellsThisSection.size();k++){
                Cell currentCell = cellsThisSection.get(k);

                //Section
                currentCell.setSection(section);

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

        Configuration.instance.AmountOfSections = sections.size();

    }
}

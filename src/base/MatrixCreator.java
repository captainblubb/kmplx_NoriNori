package base;

import Backtracking.CellBT;
import Backtracking.SectionBT;
import configuration.Configuration;
import task.TaskData;

import java.awt.*;
import java.util.ArrayList;

import static configuration.Configuration.GRID_SIZE;

public class MatrixCreator {


    static ArrayList<Section> sections;
    static ArrayList<SectionBT> sectionsBT;


    public static Cell[][] createEvaMatrixFromTask(){

        Cell[][]  matrix = new base.Cell[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                matrix[i][j] = new base.Cell(i, j);
            }
        }
        setMatrixWithTaskDataForEva(TaskData.getSectionsForTask(),matrix);

        return matrix;
    }

    public static CellBT[][] createBTMatrixFromTask(){
        CellBT[][]  matrixBT = new CellBT[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                matrixBT[i][j] = new CellBT(i, j);

            }
        }
        setMatrixWithTaskDataForBT(TaskData.getSectionsForTask(),matrixBT);

        return matrixBT;
    }

    public static ArrayList<Section> getSectionsOfLastMatrix() {
        return sections;
    }

    public static ArrayList<SectionBT> getSectionsOfLastMatrixBT() {
        return sectionsBT;
    }

    private static void setMatrixWithTaskDataForEva(ArrayList<ArrayList<Point>> pointArray, Cell[][] matrix){

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
                section.incCellsThisSection();

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

    private static void setMatrixWithTaskDataForBT(ArrayList<ArrayList<Point>> pointArray, CellBT[][] matrix){

        sectionsBT = new ArrayList<>();
        for(int i = 0; i<pointArray.size(); i++){
            SectionBT section = new SectionBT();
            sectionsBT.add(section);
            ArrayList<CellBT> cellsThisSection = new ArrayList<>();
            for(int l = 0 ; l<pointArray.get(i).size();l++){
                cellsThisSection.add(matrix[pointArray.get(i).get(l).x][pointArray.get(i).get(l).y]);
            }

            for(int k = 0; k<cellsThisSection.size();k++){
                CellBT currentCell = cellsThisSection.get(k);

                //Section
                currentCell.setSectionBT(section);
                section.addCellBT(currentCell);

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

    public static String chromaToString(int[][] chromaMatrix){
        String chromosomString = "";
        for (int y = 0; y<Configuration.GRID_SIZE;y++){
            for(int x = 0; x<Configuration.GRID_SIZE;x++){
                chromosomString=chromosomString+ "["+chromaMatrix[x][y]+"] ";
            }
            chromosomString=chromosomString+"\n";
        }
        return chromosomString;
    }
}

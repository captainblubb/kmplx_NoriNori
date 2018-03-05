package Backtracking;

import base.MatrixCreator;
import base.Section;
import configuration.Configuration;
import configuration.Direction;
import gui.Controller;
import gui.IController;

import java.awt.*;
import java.util.ArrayList;

public class BacktrackingManager {

    ArrayList<SectionBT> sectionsBT;
    Controller controller;
    CellBT[][] matrixBT;

    public BacktrackingManager(Controller controller){
        this.controller = controller;
        matrixBT= MatrixCreator.createBTMatrixFromTask();
        sectionsBT=MatrixCreator.getSectionsOfLastMatrixBT();
        controller.setMatrixToGridCellsBT(matrixBT);
        setDominoInSectionBTWith2CellsBT();
        controller.setMatrixToGridCellsBT(matrixBT);
    }

    public void doBT(){
        while (inEverySectionOneDomino()){
            for (int i = 0; i<sectionsBT.size(); i++){






            }
        }

    }

    private boolean inEverySectionOneDomino(){

        for(int i =0 ; i <sectionsBT.size(); i++){
            if(sectionsBT.get(i).getDominosThisSection()==2){
                return true;
            }
        }
        return false;
    }


    private boolean isDominoPlaceableInThis2Cells(ArrayList<CellBT> cellsBT){
        //Nur 2 Zellen und in beiden darf noch kein Domino gesetzt sein
        if(cellsBT.size()==2 && isDominoPlaceableInThisCell(cellsBT.get(0)) && isDominoPlaceableInThisCell(cellsBT.get(1))){
            //In der ersten Zelle darf in dem Bereich in dem sie Sich befindet erst max 1 domino gesetzt sein
            if(cellsBT.get(0).getSectionBT().getDominosThisSection()<2){
                //Abstand zwischen zellen genau 1
                if(Math.abs(cellsBT.get(0).getColPos()+cellsBT.get(0).getRowPos()-cellsBT.get(1).getColPos()-cellsBT.get(1).getRowPos())==1) {
                    //Keine direkten Dominos nebenan
                    if(hasDominosDirectNeighbours(cellsBT.get(0),cellsBT.get(1))) {
                        //Zellen in der gleichen Section?
                        if (cellsBT.get(0).getSectionBT() == cellsBT.get(1).getSectionBT()) {
                            // JA: es darf kein anderer Domino in der Zelle sein
                            if (cellsBT.get(1).getSectionBT().getDominosThisSection() == 0) {
                                setDominoInto2Cells(cellsBT.get(0), cellsBT.get(1));
                                //TODO verschiebung in der Section ??
                                return true;
                            }
                        } else {
                            //Nein ! Zelle 2 in einer anderen Section => dort darf max 2 sein
                            if (cellsBT.get(1).getSectionBT().getDominosThisSection() < 2) {
                                setDominoInto2Cells(cellsBT.get(0), cellsBT.get(1));
                                //TODO Verschiebung in der Section ??
                                return true;
                            } else {
                                //TODO VERSCHIEBUNG in der Section ??
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //Hilfsmethode
    private boolean isDominoPlaceableInThisCell(CellBT cellBT){
        if(cellBT.getDominoPlacedDirection() == Direction.NONE){
            return true;
        }
        return false;
    }

    //DOmino(CellBt,CellBT) überprüfung neighbourh
    private boolean hasDominosDirectNeighbours(CellBT cell1, CellBT cell2){
        if(hasCellNoNeighbourghs(cell1.getRowPos(),cell1.getRowPos(),cell2)){
            return false;
        }
        if (hasCellNoNeighbourghs(cell2.getRowPos(),cell2.getColPos(),cell1)){
            return false;
        }
        return true;
    }
    //Hilfsmethode
    private boolean hasCellNoNeighbourghs(int x, int y , CellBT cell2){
        if(x>0 && matrixBT[x-1][y] != cell2 && matrixBT[x-1][y].getDominoPlacedDirection()!=Direction.NONE){
            return false;
        }
        if(x<Configuration.GRID_SIZE && matrixBT[x+1][y] != cell2 && matrixBT[x+1][y].getDominoPlacedDirection()!=Direction.NONE){
            return false;
        }
        if(y>0 && matrixBT[x][y-1] != cell2 && matrixBT[x][y-1].getDominoPlacedDirection()!=Direction.NONE){
            return false;
        }
        if(y<Configuration.GRID_SIZE && matrixBT[x][y+1] != cell2 && matrixBT[x][y+1].getDominoPlacedDirection()!=Direction.NONE){
            return false;
        }

        return true;
    }



    public void setDominoInSectionBTWith2CellsBT(){
        for (int i = 0; i<sectionsBT.size();i++){
            if (sectionsBT.get(i).getCellsThisSection().size()==2){
                CellBT currentCell1 = sectionsBT.get(i).getCellsThisSection().get(0);
                CellBT currentCell2 = sectionsBT.get(i).getCellsThisSection().get(1);
                setDominoInto2Cells(currentCell1,currentCell2);

            }
        }
    }

    public void setDominoInto2Cells(CellBT currentCell1, CellBT currentCell2){
        if (currentCell1.getRowPos()-currentCell2.getRowPos() == -1){
            currentCell1.setDomino(Direction.Right);
            currentCell2.setDomino(Direction.LEFT);
        }else if(currentCell1.getRowPos()-currentCell2.getRowPos() == 1){
            currentCell1.setDomino(Direction.LEFT);
            currentCell2.setDomino(Direction.Right);
        }else if (currentCell1.getColPos()-currentCell2.getColPos() == -1){
            currentCell1.setDomino(Direction.BOTTOM);
            currentCell2.setDomino(Direction.TOP);
        }else if(currentCell1.getColPos()-currentCell2.getColPos() == 1){
            currentCell1.setDomino(Direction.TOP);
            currentCell2.setDomino(Direction.BOTTOM);
        }
    }


    private SectionBT pointBelongsToSectionBT (int x, int y){
        return matrixBT[x][y].getSectionBT();
    }


    private boolean isDominoRightPlaceable(int x, int y){
        if(matrixBT[x][y].isDominoRightPlaceable()
                && matrixBT[x][y].getDominoPlacedDirection() == Direction.NONE
                && matrixBT[x+1][y].getDominoPlacedDirection() == Direction.NONE){
            return true;
        }
        return false;
    }

    private boolean isDominoLeftPlaceable(int x, int y){
        if(matrixBT[x][y].isDominoLeftPlaceable()
                && matrixBT[x][y].getDominoPlacedDirection() == Direction.NONE
                && matrixBT[x-1][y].getDominoPlacedDirection() == Direction.NONE){
            return true;
        }
        return false;
    }

    private boolean isDominoTopPlaceable(int x, int y){
        if(matrixBT[x][y].isDominoTopPlaceable()
                && matrixBT[x][y].getDominoPlacedDirection() == Direction.NONE
                && matrixBT[x][y-1].getDominoPlacedDirection() == Direction.NONE){
            return true;
        }
        return false;
    }

    private boolean isDominoBottomPlaceable(int x, int y){
        if(matrixBT[x][y].isDominoBotPlaceable()
                && matrixBT[x][y].getDominoPlacedDirection() == Direction.NONE
                && matrixBT[x][y+1].getDominoPlacedDirection() == Direction.NONE){
            return true;
        }
        return false;
    }



}

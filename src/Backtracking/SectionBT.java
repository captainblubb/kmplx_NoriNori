package Backtracking;

import configuration.Direction;

import java.util.ArrayList;

public class SectionBT {

    private static int idCounter;
    private ArrayList<CellBT> cellsThisSection;
    private int sectionID;

    public SectionBT(){
        idCounter++;
        sectionID= idCounter;
        cellsThisSection=new ArrayList<>();
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public void addCellBT(CellBT cellBT){
        cellsThisSection.add(cellBT);
    }

    public int getDominosThisSection(){
        int dominosThisSectionCounter =0;
        for (int i =0; i<cellsThisSection.size();i++){
            if (cellsThisSection.get(i).getDominoPlacedDirection() != Direction.NONE){
                dominosThisSectionCounter++;
            }
        }
        return dominosThisSectionCounter;
    }

    public ArrayList<CellBT> getCellsThisSection() {
        return cellsThisSection;
    }

}

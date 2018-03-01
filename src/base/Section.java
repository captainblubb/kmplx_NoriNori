package base;

public class Section {

    private static int idCounter;

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    private int cellsThisSection;
    private int sectionID;
    private int dominoCount;

    public Section(){
        idCounter++;
        sectionID= idCounter;
        dominoCount = 0;
        cellsThisSection=0;
    }

    public void incCellsThisSection(){
        cellsThisSection++;
    }

    public int getCellsThisSection() {
        return cellsThisSection;
    }

    public void increaseDominoCount(){
        dominoCount++;
    }

    public void decreaseDominoCount(){
        if(dominoCount>0) {
            dominoCount--;
        }
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getDominoCount() {
        return dominoCount;
    }

    public void setDominoCount(int dominoCount) {
        dominoCount = dominoCount;
    }



}

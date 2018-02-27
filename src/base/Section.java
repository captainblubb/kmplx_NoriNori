package base;

public class Section {

    private static int idCounter;
    private int sectionID;
    private int dominoCount;

    public Section(){
        idCounter++;
        sectionID= idCounter;
        dominoCount = 0;
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

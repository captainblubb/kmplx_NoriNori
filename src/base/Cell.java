package base;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Cell extends javafx.scene.layout.Pane {

    private int currentStateIndex = 0;
    private CellColor currentCellColor = CellColor.WHITE;
    private int belongsToSectionIndex = -1;

    private boolean isDominoSet = false;

    private int rowPos;
    private int colPos;

    private int topBorder,rightBorder,bottomBorder,leftBorder;

    public Cell(int rowPos, int colPos ) {
        super();
        super.setStyle("-fx-background-color: lightgray");
        drawBorder(1,1,1,1);
        this.colPos = colPos;
        this.rowPos = rowPos;
    }


    public void drawBorder( int top, int right, int bottom, int left){
        if(left>=0 && top>=0 && right>=0 &&bottom>=0){
            super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(top,right,bottom,left))));
            topBorder=top;
            rightBorder=right;
            bottomBorder=bottom;
            leftBorder=left;
        }
    }

    public boolean isDominoLeftPlaceable(){
        if(rowPos==0){
            return false;
        }
        return true;
    }

    public boolean isDominoRightPlaceable(){
        if(rowPos==9){
            return false;
        }
        return true;
    }

    public boolean isDominoTopPlaceable(){
        if(colPos==0){
            return false;
        }
        return true;
    }

    public boolean isDominoBotPlaceable(){
        if(colPos==9){
            return false;
        }
        return true;
    }

    public void drawBorderLeft(int left){
        if(left>=0){
            super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(topBorder,rightBorder,bottomBorder,left))));
            leftBorder=left;
        }
    }

    public void drawBorderTop(int top){
        if(top>=0){
            super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(top,rightBorder,bottomBorder,leftBorder))));
            topBorder=top;
        }
    }

    public void drawBorderRight(int right){
        if(right>=0){
            super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(topBorder,right,bottomBorder,leftBorder))));
            rightBorder=right;
        }
    }

    public void drawBorderBottom(int bottom){
        if(bottom>=0){
            super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(topBorder,rightBorder,bottom,leftBorder))));
            bottomBorder= bottom;
        }
    }

    public int getRowPos() {
        return rowPos;
    }

    public int getColPos() {
        return colPos;
    }

    public int getCurrentStateIndex() {
        return currentStateIndex;
    }

    public boolean isDominoSet() {
        return isDominoSet;
    }


    public int getBelongsToSectionIndex() {
        return belongsToSectionIndex;
    }

    public void setBelongsToSectionIndex(int belongsToSectionIndex) {
        this.belongsToSectionIndex = belongsToSectionIndex;
    }

    public void setDominoSet(boolean dominoSet) {
        isDominoSet = dominoSet;
    }

    private void setColor(){
        if(isDominoSet) {
            super.setStyle("-fx-background-color: black");
        }else{
            super.setStyle("-fx-background-color: lightgray");
        }
    }
}

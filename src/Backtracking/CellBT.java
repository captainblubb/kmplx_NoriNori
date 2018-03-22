package Backtracking;


import base.Section;
import configuration.Direction;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CellBT extends javafx.scene.layout.Pane {

    private Direction dominoPlacedDirection;
    private SectionBT sectionBT;
    private int rowPos;
    private int colPos;
    private int topBorder,rightBorder,bottomBorder,leftBorder;

    public CellBT(int rowPos, int colPos ) {
        super();
        super.setStyle("-fx-background-color: lightgray;");
        drawBorder(1,1,1,1);
        this.colPos = colPos;
        this.rowPos = rowPos;
        dominoPlacedDirection = Direction.NONE;
    }

    public boolean setDomino(Direction direction){
        if (dominoPlacedDirection == Direction.NONE){
            this.dominoPlacedDirection= direction;
            setColor();
            return true;
        }
        return false;
    }

    public Direction getDominoPlacedDirection() {
        return dominoPlacedDirection;
    }

    public int getRowPos() {
        return rowPos;
    }

    public int getColPos() {
        return colPos;
    }

    public void setSectionBT(SectionBT sectionBT){
        this.sectionBT = sectionBT;
    }

    public SectionBT getSectionBT(){
        return sectionBT;
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

    public void setDominoPlacedDirection(Direction dominoPlacedDirection){
        this.dominoPlacedDirection = dominoPlacedDirection;
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

    private void setColor(){
        if(dominoPlacedDirection != Direction.NONE) {
            super.setStyle("-fx-background-color: Red");
            // super.setStyle("-fx-background-image: url('/resouces/Domino.png')");
        }else{
            super.setStyle("-fx-background-color: lightgray");
        }
    }
}

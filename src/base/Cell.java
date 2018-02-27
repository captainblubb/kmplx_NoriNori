package base;

import configuration.Configuration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class Cell extends javafx.scene.layout.Pane {

    private int currentStateIndex = 0;
    private int visitedCounter = 0;
    private CellColor currentCellColor = CellColor.WHITE;

    private int rowPos;
    private int colPos;

    private int topBorder,rightBorder,bottomBorder,leftBorder;

    public Cell(int colPos, int rowPos ) {
        super();
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
            leftBorder=right;
        }
    }

    public void drawBorderBottom(int bottom){
        if(bottom>=0){
            super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(topBorder,rightBorder,bottom,leftBorder))));
            bottom=leftBorder;
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

    public int getVisitedCounter() {
        return visitedCounter;
    }

    public void nextState() {
        invertColor();
        visitedCounter++;
    }

    private void invertColor() {
        if (currentCellColor == CellColor.WHITE) {
           // super.setFill(Paint.valueOf("000000"));
            currentCellColor = CellColor.BLACK;
        } else {
       //super.setFill(Paint.valueOf("FFFFFF"));
            currentCellColor = CellColor.WHITE;
        }
    }



}

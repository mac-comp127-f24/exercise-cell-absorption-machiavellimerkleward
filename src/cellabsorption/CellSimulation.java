package cellabsorption;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    private List<Cell> cells;

    private CanvasWindow canvas;
    private Random rand = new Random();

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();
        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            for(Cell cell : cells) {
                cell.moveAround(canvasCenter);
                cell.grow(0.02);                
            }  
            canvas.draw();
            canvas.pause(10); 
        }
    }

    private void populateCells() {
        cells = new ArrayList<>();
        for(int i = 0; i < 200; i++){
            double size = rand.nextInt(5) + 2;
            Cell cell = new Cell(
                rand.nextDouble() * (canvas.getWidth() - size),
                rand.nextDouble() * (canvas.getWidth() - size),
                size,
                Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            cells.add(cell);
            canvas.add(cell.getShape());
        }
    }

    private static double sqr(double x) {
        return x * x;
    }

    private void handleCellInteraction() {
        for(int i = 0; i < 200; i++){
            cells.get(i);
            for(int j = (i + 1); j < 200;) {
                cells.get(j);
                this.cell.interactWith(j);
            }
        }
        // for i from 0 up to max cell index
            // get cell at index i
            // for j from i+1 up to max cell index
                // get cell at index j
                // make the two cells interact
    }
}

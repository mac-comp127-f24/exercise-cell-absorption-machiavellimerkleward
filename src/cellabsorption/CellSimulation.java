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
                handleCellInteraction();     
                canvas.draw();     
            }  
            System.out.println("Hello");
            
            canvas.draw();
            canvas.pause(10); 
            

        }
    }

    private void populateCells() {
        cells = new ArrayList<>();
        for(int i = 0; i < 199; i++){
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
        for(int i = 0; i < cells.size(); i++){
            Cell cell1 = cells.get(i);
            for(int j = (i + 1); j < cells.size(); j++) {
                Cell cell2 = cells.get(j);
                cell1.interactWith(cell2);
            }
        }
        // for i from 0 up to max cell index
            // get cell at index i
            // for j from i+1 up to max cell index
                // get cell at index j
                // make the two cells interact
    }
}

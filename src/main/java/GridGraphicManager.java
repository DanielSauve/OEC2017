import javafx.animation.KeyValue;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by David on 27/01/17.
 */
public class GridGraphicManager {

    private GraphicPanel panel;
    private int scaleFactor;
    private ArrayList<Pair<Integer, Integer>> generators;
    private ArrayList<Pair<Integer, Integer>> controlNodes;
    private ArrayList<Pair<Integer, Integer>> houses;
    private ArrayList<Pair<Integer, Integer>> edges;


    public GridGraphicManager(GraphicPanel newPanel) {
        this.panel = newPanel;
        this.scaleFactor = 1;
    }

    public void drawGrid(Graphics g1) {
        Graphics2D g2d = (Graphics2D) g1;

        g2d.setPaint(Color.BLACK);

        g2d.drawLine(10,10,100,100);
    }

    private void drawHouse(Graphics2D g2d, int x, int y) {
        g2d.drawRect(x, y, this.scaleFactor*10, this.scaleFactor*10);
    }

    private void drawGenerator(Graphics2D g2d, int x, int y) {
        int xset[] = {this.scaleFactor*x,this.scaleFactor*x+5,this.scaleFactor*x+5};
        int yset[] = {this.scaleFactor*y,this.scaleFactor*y+10,this.scaleFactor*y};
        g2d.drawPolygon(xset, yset, 3);
    }

    private void drawControl(Graphics2D g2d, int x, int y) {
        g2d.drawOval(this.scaleFactor*x, this.scaleFactor*y, 10, 5);
    }

    public class Edge {

        public Integer x1;
        public Integer y1;
        public Integer x2;
        public Integer y2;

        public Edge(Integer x1, Integer y1, Integer x2, Integer y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

}

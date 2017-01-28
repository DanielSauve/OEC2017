import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by David on 27/01/17.
 */
public class GridGraphicManager {

    private Integer positionX;
    private Integer positionY;

    private GraphicPanel panel;
    private int scaleFactor;
    private ArrayList<Pair<Integer, Integer>> generators;
    private ArrayList<Pair<Integer, Integer>> controlNodes;
    private ArrayList<Pair<Integer, Integer>> houses;
    private ArrayList<Edge> edges;


    public GridGraphicManager(GraphicPanel newPanel) {
        this.panel = newPanel;
        this.scaleFactor = 1;
        this.positionX = 0;
        this.positionY = 0;

        this.generators = new ArrayList<Pair<Integer, Integer>>();
        this.controlNodes = new ArrayList<Pair<Integer, Integer>>();
        this.houses = new ArrayList<Pair<Integer, Integer>>();
        this.edges = new ArrayList<Edge>();

        this.setupGrid();
    }

    private void setupGrid() {

        //Generators
        generators.add(new Pair<Integer, Integer>(0, 0));
        generators.add(new Pair<Integer, Integer>(-100, -100));
        generators.add(new Pair<Integer, Integer>(100, -100));

        //Control Nodes
        controlNodes.add(new Pair<Integer, Integer>(-100, -200));
        controlNodes.add(new Pair<Integer, Integer>(-200, -250));
        controlNodes.add(new Pair<Integer, Integer>(0, -250));
        controlNodes.add(new Pair<Integer, Integer>(-100, -300));
        controlNodes.add(new Pair<Integer, Integer>(-200, -350));
        controlNodes.add(new Pair<Integer, Integer>(0, -350));      //A1-06
        controlNodes.add(new Pair<Integer, Integer>(-200, -450));   //A1-08
        controlNodes.add(new Pair<Integer, Integer>(0, -450));   //A1-09
        controlNodes.add(new Pair<Integer, Integer>(-100, -400));   //A1-07
        controlNodes.add(new Pair<Integer, Integer>(-100, -500));   //A1-10

        controlNodes.add(new Pair<Integer, Integer>(200, -100));   //A2-01
        controlNodes.add(new Pair<Integer, Integer>(350, -100));   //A2-04
        controlNodes.add(new Pair<Integer, Integer>(300, -200));   //A2-02
        controlNodes.add(new Pair<Integer, Integer>(450, -200));   //A2-06
        controlNodes.add(new Pair<Integer, Integer>(450, 0));   //A2-05
        controlNodes.add(new Pair<Integer, Integer>(300, 0));   //A2-03
        controlNodes.add(new Pair<Integer, Integer>(600, 0));   //A2-07

        //Houses
        houses.add(new Pair<Integer, Integer>(300, 100)); //81
        houses.add(new Pair<Integer, Integer>(300, 200)); //82

        houses.add(new Pair<Integer, Integer>(450, 100)); //91
        houses.add(new Pair<Integer, Integer>(450, 200)); //92

        houses.add(new Pair<Integer, Integer>(700, 0)); //61
        houses.add(new Pair<Integer, Integer>(800, 0)); //62
        houses.add(new Pair<Integer, Integer>(900, 0)); //63
        houses.add(new Pair<Integer, Integer>(1000, 0)); //64
        houses.add(new Pair<Integer, Integer>(1100, 0)); //65

        houses.add(new Pair<Integer, Integer>(1100, 0)); //65

    }

    public void drawGrid(Graphics g1) {
        Graphics2D g2d = (Graphics2D) g1;

        g2d.setPaint(Color.BLACK);

        for( Pair<Integer, Integer> gen : generators ) {
            drawGenerator(g2d, this.scaleFactor*(this.positionX+gen.getKey()), this.scaleFactor*(this.positionY+gen.getValue()));
        }

        for( Pair<Integer, Integer> controlNode : controlNodes ) {
            drawControl(g2d, this.scaleFactor*(this.positionX+controlNode.getKey()), this.scaleFactor*(this.positionY+controlNode.getValue()));
        }
        for( Pair<Integer, Integer> house : houses ) {
            drawHouse(g2d, this.scaleFactor*(this.positionX+house.getKey()), this.scaleFactor*(this.positionY+house.getValue()));
        }

        for( Edge edge : edges ) {
            g2d.drawLine(edge.x1,edge.y1,edge.x2,edge.y2);
        }

    }

    private void drawHouse(Graphics2D g2d, int x, int y) {
        g2d.drawRect(x, y, this.scaleFactor*50, this.scaleFactor*50);
    }

    private void drawGenerator(Graphics2D g2d, int x, int y) {
        int xset[] = {this.scaleFactor*x,this.scaleFactor*x+25,this.scaleFactor*x+50};
        int yset[] = {this.scaleFactor*y,this.scaleFactor*y-50,this.scaleFactor*y};
        g2d.drawPolygon(xset, yset, 3);
    }

    private void drawControl(Graphics2D g2d, int x, int y) {
        g2d.drawOval(this.scaleFactor*x, this.scaleFactor*y, this.scaleFactor*50, this.scaleFactor*25);
    }

    public void increaseZoom() {
        if(this.scaleFactor < 10) {
            this.scaleFactor += 1;
        }
        panel.repaint();
    }

    public void decreaseZoom() {
        if(this.scaleFactor > 1) {
            this.scaleFactor -= 1;
        }
        panel.repaint();
    }

    public void moveUp() {
        this.positionY += 50;
        panel.repaint();
    }

    public void moveDown() {
        this.positionY -= 50;
        panel.repaint();
    }

    public void moveLeft() {
        this.positionX += 50;
        panel.repaint();
    }

    public void moveRight() {
        this.positionX -= 50;
        panel.repaint();
    }

    public class Edge {

        public Integer x1;
        public Integer y1;
        public Integer x2;
        public Integer y2;

        public String text;

        public Integer mousex1;
        public Integer mousey1;
        public Integer mousex2;
        public Integer mousey2;


        public Edge(String text, Integer x1, Integer y1, Integer x2, Integer y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.text = text;

            if(x1 < x2) {
                this.mousex1 = x1;
                this.mousex2 = x2;
            } else {
                this.mousex1 = x2;
                this.mousex2 = x1;
            }

            if(y1 < y2) {
                this.mousey1 = y1;
                this.mousey2 = y2;
            } else {
                this.mousey1 = y2;
                this.mousey2 = y1;
            }
        }
    }

    public class House {
        private Integer x;
        private Integer y;
        private Integer size;

    }

}

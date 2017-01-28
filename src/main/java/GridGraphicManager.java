import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by David on 27/01/17.
 */
public class GridGraphicManager {

    //This moves the grid when directional buttons clicked
    private Integer positionX;
    private Integer positionY;

    private GraphicPanel panel;
    private int scaleFactor;
    private ArrayList<Generator> generators;
    private ArrayList<Pair<Integer, Integer>> controlNodes;
    private ArrayList<Pair<Integer, Integer>> houses;
    private ArrayList<Edge> edges;


    public GridGraphicManager(GraphicPanel newPanel) {
        this.panel = newPanel;
        this.scaleFactor = 1;
        this.positionX = 200;
        this.positionY = 200;

        this.generators = new ArrayList<Generator>();
        this.controlNodes = new ArrayList<Pair<Integer, Integer>>();
        this.houses = new ArrayList<Pair<Integer, Integer>>();
        this.edges = new ArrayList<Edge>();

        this.setupGrid();
    }

    private void setupGrid() {

        Integer generatorSize = 50;
        Integer houseSize = 50;
        Integer controlSize = 50;

        //Generators
        generators.add(new Generator(0, 0, generatorSize, "B1"));
        generators.add(new Generator(-100, -100, generatorSize, "B1"));
        generators.add(new Generator(100, -100, generatorSize, "B1"));

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

        houses.add(new Pair<Integer, Integer>(700, 0)); //71
        houses.add(new Pair<Integer, Integer>(800, 0)); //72
        houses.add(new Pair<Integer, Integer>(900, 0)); //73

        houses.add(new Pair<Integer, Integer>(700, -200)); //65
        houses.add(new Pair<Integer, Integer>(800, -200)); //65
        houses.add(new Pair<Integer, Integer>(900, -200)); //65
        houses.add(new Pair<Integer, Integer>(1000, -200)); //65
        houses.add(new Pair<Integer, Integer>(1100, -200)); //65

        houses.add(new Pair<Integer, Integer>(100, -450)); //41
        houses.add(new Pair<Integer, Integer>(200, -450)); //42
        houses.add(new Pair<Integer, Integer>(300, -450)); //43

        houses.add(new Pair<Integer, Integer>(100, -270)); //51
        houses.add(new Pair<Integer, Integer>(200, -270)); //52
        houses.add(new Pair<Integer, Integer>(300, -270)); //53


        houses.add(new Pair<Integer, Integer>(-300, -270)); //11
        houses.add(new Pair<Integer, Integer>(-400, -270)); //12
        houses.add(new Pair<Integer, Integer>(-500, -270)); //13

        houses.add(new Pair<Integer, Integer>(-300, -470)); //21
        houses.add(new Pair<Integer, Integer>(-400, -470)); //22
        houses.add(new Pair<Integer, Integer>(-500, -470)); //23

        houses.add(new Pair<Integer, Integer>(-100, -570)); //31
        houses.add(new Pair<Integer, Integer>(-100, -670)); //32
        houses.add(new Pair<Integer, Integer>(-100, -770)); //33
        houses.add(new Pair<Integer, Integer>(-100, -870)); //34
    }

    public void drawGrid(Graphics g1) {
        Graphics2D g2d = (Graphics2D) g1;

        g2d.setPaint(Color.BLACK);

        for( Generator gen : generators ) {
            drawGenerator(g2d, this.scaleFactor*(this.positionX+gen.x), this.scaleFactor*(this.positionY+gen.y));
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
        public Integer x;
        public Integer y;
        public Integer size;
        public String text;

        public Integer mousex1;
        public Integer mousey1;
        public Integer mousex2;
        public Integer mousey2;

        public House(Integer x, Integer y, Integer size, String text) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.text = text;

            mousex1 = x;
            mousex2 = x + size;
            mousey1 = y;
            mousey2 = y + size;
        }

    }

    public class ControlNode {
        public Integer x;
        public Integer y;
        public String text;

        public Integer mousex1;
        public Integer mousey1;
        public Integer mousex2;
        public Integer mousey2;

        public ControlNode(Integer x, Integer y, Integer size, String text) {
            this.x = x;
            this.y = y;
            this.text = text;

            mousex1 = x;
            mousex2 = x + size;
            mousey1 = y;
            mousey2 = y + (size/2);
        }
    }

    public class Generator {
        public Integer x;
        public Integer y;
        public String text;

        public Integer mousex1;
        public Integer mousey1;
        public Integer mousex2;
        public Integer mousey2;

        public Generator(Integer x, Integer y, Integer size, String text) {
            this.x = x;
            this.y = y;
            this.text = text;

            mousex1 = x;
            mousex2 = x + size;
            mousey1 = y;
            mousey2 = y + size;
        }
    }

}

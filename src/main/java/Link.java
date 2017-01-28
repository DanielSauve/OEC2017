import java.util.ArrayList;
import java.util.List;

/**
 * Created by puih123 on 2017-01-27.
 */
public class Link extends SuperNode{

    Float cost;
    ArrayList<SuperNode> neighbours;

    public Link(Float cost) {
        this.cost = cost;
        this.neighbours = new ArrayList<SuperNode>();
    }

    public boolean equals(Object o) {

        if (!(o instanceof Link)) {
            return false;
        }

        Link l = (Link) o;

        return(l.cost.equals(this.getCost())
                && l.getNeighbours().containsAll(this.getNeighbours())
                && this.getNeighbours().containsAll(l.getNeighbours()));

    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public void addNeighbour(SuperNode node) {
        this.neighbours.add(node);
    }
    public ArrayList<SuperNode> getNeighbours() {
        return neighbours;
    }

}

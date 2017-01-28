/**
 * Created by puih123 on 2017-01-27.
 */
public class Link {

    int cost;
    ArrayList<SuperNode> neighbours;

    public Link(int cost) {
        this.cost = cost;
        this.neighbours = new ArrayList<SuperNode>();
    }

    public void addNeighbour(SuperNode node) {
        this.neighbours.add(node);
    }

}

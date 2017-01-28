/**
 * Created by puih123 on 2017-01-27.
 */
public class Link {

    Float cost;
    ArrayList<SuperNode> neighbours;

    public Link(Float cost) {
        this.cost = cost;
        this.neighbours = new ArrayList<SuperNode>();
    }

    public void addNeighbour(SuperNode node) {
        this.neighbours.add(node);
    }

    public List<SuperNode> getNeighbours(){
        return neighbours;
    }

    public Float getCost(){
        return cost;
    }

}

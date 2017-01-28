import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielsauve on 2017-01-27.
 */
public class PathFinder {
    HashMap<SuperNode, SuperNode> previousNode;
    HashMap<SuperNode, Float> costToNode;
    SuperNode source;
    List<SuperNode> copyGraph = new ArrayList<SuperNode>();

    public PathFinder(SuperNode source, List<SuperNode> graph){
        previousNode = new HashMap<SuperNode, SuperNode>();
        costToNode = new HashMap<SuperNode, Float>();
        this.source = source;
        this.execute(graph);
    }

    /**
     * Resets the hashmaps for cost and previous node for graph. Also recreates copy of graph
     * @param graph
     */
    private void resetCostAndPreviousNodes(List<SuperNode> graph) {
        copyGraph.clear();
        for (SuperNode superNode: graph){
            copyGraph.add(superNode);
            previousNode.put(source, null);
            if (superNode.equals(source)){
                costToNode.put(superNode, 0f);
            } else {
                costToNode.put(superNode, Float.MAX_VALUE);
            }
        }
    }

    public void execute(List<SuperNode> graph){
        this.resetCostAndPreviousNodes(graph);

        //Set the cost to the neighbours of the source not first before entering algorithm
        for (Link link: source.getLinks()){
            List<SuperNode> nodes = link.getNeighbours();
            for (SuperNode node: nodes){
                if (!node.equals(source)){
                    costToNode.put(node, link.getCost());
                }
            }
        }

        this.dijkstras();

    }

    private void dijkstras() {

        while (!copyGraph.isEmpty()){

            //Find the node with the minimum distance in graph
            Float minimumDistance = Float.MAX_VALUE;
            SuperNode minimunNode = null;
            for(SuperNode node: copyGraph){
                Float temp = costToNode.get(node);
                if (temp < minimumDistance){
                    minimumDistance = temp;
                    minimunNode = node;
                }
            }

            copyGraph.remove(minimunNode);  //remove minimum node from list

            //find and set minimum link from minimum node to graph
            for (Link link: minimunNode.getLinks()){
                List<SuperNode> nodes = link.getNeighbours();
                SuperNode neighbour = null;
                for (SuperNode node: nodes){
                    if (!node.equals(minimunNode)){
                        neighbour = node;
                    }
                }
                if (costToNode.get(neighbour) > link.getCost() + minimumDistance){
                    costToNode.put(neighbour, link.getCost() + minimumDistance);
                    previousNode.put(neighbour, minimunNode);
                }
            }

        }
    }

    /**
     * returns cost in cents/kwh/distance for particular path.
     * @param distance
     * @param path
     * @return
     */
    public Float getCostOfPath(Float distance, List<SuperNode> path) {

        Float cost = 5F * 3000 * distance;

        for (SuperNode node: path) {

            if (node instanceof Generator && !node.equals(source)) {
                cost += 10F * 3000;
                if (((Generator) node).getCompany().equals(((Generator) source).getCompany())) {
                    cost += 30F * 3000;
                }
            }
        }

        return cost;   //Do all math in cents so that can convert to dollars after
    }


    public List<SuperNode> getPath(SuperNode node){
        List<SuperNode> path = new ArrayList<SuperNode>();
        SuperNode temp = node;
        path.add(temp);
        while (!(previousNode.get(temp) == null)){
            path.add(previousNode.get(temp));
            temp = previousNode.get(temp);
        }
        return path;
    }

    public Float getCost(SuperNode node){
        return costToNode.get(node);
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielsauve on 2017-01-27.
 * Implementation of Djikstra's shortest path algorithm. Will find the shortest path from the source node
 * to any other node on the graph.
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

    /**
     * Creates the initial state of the algorithm where we have the source node and every adjacent node to the source
     * @param graph The graph containing all the nodes
     */
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

    /**
     * Executes djikstra's algorithm on the entire graph, finding the shortest path to each node based on the cost
     * of the links
     */
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
     * @param distance The total distance to travel
     * @param path The path that will be travelled
     * @return The cost in dollars of the path
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


    /**
     * Uses the generated paths above to create the shortest path from a node to the source node
     * @param node The node we are trying to find the path to
     * @return The shortest path from the source node, to the parameter node
     */
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

    /**
     *
     * @param node The node we want to find the cost to reach from the source node
     * @return The cost to reach the node from the source node
     */
    public Float getCost(SuperNode node){
        return costToNode.get(node);
    }
}

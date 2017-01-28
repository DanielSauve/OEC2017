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

    public PathFinder(SuperNode source, List<SuperNode> graph){
        previousNode = new HashMap<SuperNode, SuperNode>();
        costToNode = new HashMap<SuperNode, Float>();
        this.source = source;
        this.execute(graph);
    }

    public void execute(List<SuperNode> graph){
        List<SuperNode> copyGraph = new ArrayList<SuperNode>();
        for (SuperNode superNode: graph){
            copyGraph.add(superNode);
            previousNode.put(source, null);
            if (superNode.equals(source)){
                costToNode.put(superNode, 0f);
            } else {
                costToNode.put(superNode, Float.MAX_VALUE);
            }
        }
        for (Link link: source.getLinks()){
            List<SuperNode> nodes = link.getNeighbours();
            for (SuperNode node: nodes){
                if (!node.equals(source)){
                    costToNode.put(node, link.getCost());
                }
            }
        }
        while (!copyGraph.isEmpty()){
            Float minimumDistance = Float.MAX_VALUE;
            SuperNode minimunNode = null;
            for(SuperNode node: graph){
                Float temp = costToNode.get(node);
                if (temp < minimumDistance){
                    minimumDistance = temp;
                    minimunNode = node;
                }
            }
            copyGraph.remove(minimunNode);
            for (Link link: minimunNode.getLinks()){
                List<SuperNode> nodes = link.getNeighbours();
                SuperNode neighbour = null;
                for (SuperNode node: nodes){
                    if (!node.equals(source)){
                        neighbour = node;
                    }
                }
                if (costToNode.get(neighbour) > costToNode.get(minimunNode) + minimumDistance){
                    costToNode.put(neighbour, costToNode.get(minimunNode) + minimumDistance);
                    previousNode.put(neighbour, minimunNode);
                }
            }

        }
    }

    public List<SuperNode> getPath(SuperNode node){
        List<SuperNode> path = new ArrayList<SuperNode>();
        SuperNode temp = node;
        path.add(temp);
        while (!temp.equals(source)){
            path.add(previousNode.get(temp));
            temp = previousNode.get(temp);
        }
        return path;
    }

    public Float getCost(SuperNode node){
        return costToNode.get(node);
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielsauve on 2017-01-28.
 * Uses PathFinders to plan out the whole power grid from generators to the houses. Will reroute from other generators
 * if a generator is over capacity
 */
public class PathPlanner {
    private HashMap<Generator, PathFinder> finders;
    private HashMap<Generator, Integer> capacity;
    private List<Generator> generators;
    private List<SuperNode> graph;
    List<List<SuperNode>> paths;
    HashMap<SuperNode, Float> costs;

    public PathPlanner(List<Generator> generators, List<SuperNode> graph){
        this.graph = graph;
        finders = new HashMap<Generator, PathFinder>();
        capacity = new HashMap<Generator, Integer>();
        for (Generator generator: generators){
            PathFinder pathFinder = new PathFinder(generator, graph);
            finders.put(generator, pathFinder);
            if(generator.company.equals("A")){
                if (generator.id == 1){
                    capacity.put(generator, 30);
                } else if (generator.id == 2){
                    capacity.put(generator, 21);
                }
            } else {
                capacity.put(generator, Integer.MAX_VALUE);
            }
        }
        this.generators = generators;
    }

    /**
     * Find the cheapest path in dollars to a node
     * @param node The node we want to find a path to
     * @return The path to the above node
     */
    public List<SuperNode> findPath(SuperNode node){
        Float cost = Float.MAX_VALUE;
        List<SuperNode> path = new ArrayList<SuperNode>();
        for (Generator generator: generators){
            if (capacity.get(generator) == 0){
                continue;
            }
            PathFinder finder = finders.get(generator);
            Float temp = finder.getCostOfPath(finder.getCost(node), finder.getPath(node));
            if (temp < cost){
                path = finder.getPath(node);
                cost = temp;
            }
        }
        return path;
    }

    /**
     * Finds the dollar cost of a path from generator to a node
     * @param node the node we want to find the cost to run
     * @return The dollar cost of running the above node
     */
    public Float findCost(SuperNode node){
        Float cost = Float.MAX_VALUE;
        for (Generator generator: generators){
            if (capacity.get(generator) == 0){
                continue;
            }
            PathFinder finder = finders.get(generator);
            Float temp = finder.getCostOfPath(finder.getCost(node), finder.getPath(node));
            if (temp < cost){
                cost = temp;
            }
        }
        return cost;
    }

    public void reduceCapacity(Generator generator){
        capacity.put(generator, capacity.get(generator) - 3);
    }

    public void getTopology(int hour) {

        paths = new ArrayList<List<SuperNode>>();
        costs = new HashMap<SuperNode, Float>();

        for (SuperNode node: graph) {
            if (node instanceof House) {
                if (((House) node).getOn(hour) == 1) {
                    Float cost = Float.MAX_VALUE;
                    PathFinder minFinder = null;
                    Generator generator = null;
                    for (PathFinder finder: finders.values() ) {
                         generator = (Generator) finder.source;
                        if (finder.getCost(node) <  cost && capacity.get(generator) - 3 > 0) {
                            cost = finder.getCost(node);
                            minFinder = finder;
                        }
                    }
                    capacity.put(generator, capacity.get(generator) - 3);
                    paths.add(minFinder.getPath(node));
                    costs.put(node, cost);
                }
            }
        }


    }

}

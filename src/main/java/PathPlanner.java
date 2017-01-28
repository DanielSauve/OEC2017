import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielsauve on 2017-01-28.
 */
public class PathPlanner {
    private HashMap<Generator, PathFinder> finders;
    private HashMap<Generator, Integer> capacity;
    private List<Generator> generators;

    public PathPlanner(List<Generator> generators, List<SuperNode> graph){
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

    public List<SuperNode> findPath(SuperNode node){
        Float cost = Float.MAX_VALUE;
        Generator takenFrom = generators.get(0);
        List<SuperNode> path = new ArrayList<SuperNode>();
        for (Generator generator: generators){
            if (capacity.get(generator) == 0){
                continue;
            }
            PathFinder finder = finders.get(generator);
            Float temp = finder.getCostOfPath(finder.getCost(node), finder.getPath(node));
            if (temp < cost){
                path = finder.getPath(node);
                takenFrom = generator;
                cost = temp;
            }
        }
        capacity.put(takenFrom, capacity.get(takenFrom) - 3);
        return path;
    }

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
}

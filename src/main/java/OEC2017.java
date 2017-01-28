/**
 * Created by danielsauve on 2017-01-20.
 * ^^ HA. sauve
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OEC2017 {
    public static void main(String[] args){
        InputFileReader reader = new InputFileReader();

        HashMap<Integer, House> houses = reader.readHouse();
        HashMap<String, Generator> generators = reader.readGenerators();
        HashMap<String, Node> nodes = reader.readNode();
        ArrayList<Link> links = reader.readLink(houses, nodes, generators);

        ArrayList<SuperNode> graph = new ArrayList<SuperNode>();
        graph.addAll(houses.values());
        graph.addAll(generators.values());
        graph.addAll(nodes.values());

        PathPlanner planner = new PathPlanner((List<Generator>) generators.values(), graph);

        for (int i = 1; i <= 8;i++) {
            for (House house:houses.values()) {
                if (house.getOn(i) == 1) {
                    List<SuperNode> path = planner.findPath(house);
                    Float cost = planner.findCost(house);
                }

            }
        }


    }
}

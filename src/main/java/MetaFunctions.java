import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielsauve on 2017-01-28.
 */
public class MetaFunctions {
    public static void generatorInfoPerHour(Generator generator, PathPlanner planner, Integer hour){
        planner.getTopology(hour);

        Float cost = 0F;

        for (SuperNode node:planner.graph) {
            if (node instanceof House) {
                if (((House) node).getOn(hour) == 1) {
                    cost += planner.costs.get(node);
                }
            }
        }

        Integer revenue = planner.paths.keySet().size() * 3000;

        int power = planner.capacity.get(generator);

        System.out.println("For Generator " + generator.toString() + " at hour " + hour.toString() + ".");
        System.out.println("Cost = " + cost);
        System.out.println("revenue = " + revenue);
        System.out.println("power remaining = " + power);


    }

    public static List<SuperNode> houseInfoPerHour(House house, PathPlanner planner, int hour){
        planner.getTopology(hour);
        return planner.paths.get(house);
    }

    public static void cutLink(SuperNode node1, SuperNode node2){
        List<Link> links = node1.getLinks();
        for (Link link: links){
            if (link.getNeighbours().contains(node2)){
                node1.removeLink(link);
                node2.removeLink(link);
            }
        }
    }

    public static List<House> getOnHousesPerHour(List<House> houseList, Integer hour){
        List<House> onHouses = new ArrayList<House>();
        for (House house: houseList){
            if(house.getOn(hour) == 1){
                onHouses.add(house);
            }
        }
        return onHouses;
    }
}

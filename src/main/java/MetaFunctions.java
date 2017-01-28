import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielsauve on 2017-01-28.
 */
public class MetaFunctions {
    public static void generatorInfoPerHour(Generator generator, List<SuperNode> graph, Integer hour){

    }

    public static List<SuperNode> houseInfoPerHour(House house, PathPlanner planner){
        return planner.findPath(house);
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

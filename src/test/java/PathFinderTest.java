import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by danielsauve on 2017-01-27.
 * Tests to ensure that the implementation of Djikstra's algorithm works
 */
public class PathFinderTest {
    private PathFinder pathFinder;
    private List<SuperNode> nodeList;

    public void setUp() throws Exception {
        Generator generator = new Generator("test", 1);

        House house1 = new House(1);

        Link link1 = new Link(2.0f);
        link1.addNeighbour(generator);
        generator.addLink(link1);
        link1.addNeighbour(house1);
        house1.addLink(link1);

        Node node = new Node("test", 1, 1);

        Link link2 = new Link(3.0f);
        link2.addNeighbour(generator);
        generator.addLink(link2);
        link2.addNeighbour(node);
        node.addLink(link2);

        Link link3 = new Link(1.0f);
        link3.addNeighbour(node);
        node.addLink(link3);
        link3.addNeighbour(house1);
        house1.addLink(link3);

        House house2 = new House(2);

        Link link4 = new Link(2.0f);
        link4.addNeighbour(house2);
        house2.addLink(link4);
        link4.addNeighbour(node);
        node.addLink(link4);

        nodeList = new ArrayList<SuperNode>();
        nodeList.add(generator);
        nodeList.add(house1);
        nodeList.add(house2);
        nodeList.add(node);
        pathFinder = new PathFinder(generator, nodeList);
    }


    public void getPath() throws Exception {
        assertEquals(pathFinder.getPath(nodeList.get(1)).size(), 2);
        assertEquals(pathFinder.getPath(nodeList.get(2)).size(), 2);
    }


    public void getCost() throws Exception {
        assertEquals(pathFinder.getCost(nodeList.get(0)), new Float(0f));
        assertEquals(pathFinder.getCost(nodeList.get(1)), new Float(2.0f));
    }

}
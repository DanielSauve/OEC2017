/**
 * Created by danielsauve on 2017-01-20.
 * ^^ HA. sauve
 */

import java.util.ArrayList;
import java.util.HashMap;

public class OEC2017 {
    public static void main(String[] args){
        InputFileReader reader = new InputFileReader();

        HashMap<Integer, House> houses = reader.readHouse();
        HashMap<String, Generator> generators = reader.readGenerators();
        HashMap<String, Node> nodes = reader.readNode();
        ArrayList<Link> links = reader.readLink(houses, nodes, generators);

    }
}

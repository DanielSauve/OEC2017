/**
 * Created by danielsauve on 2017-01-20.
 * ^^ HA. sauve
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OEC2017 {
    public static void main(String[] args) {
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

        for (int i = 1; i <= 8; i++) {
            for (House house : houses.values()) {
                if (house.getOn(i) == 1) {
                    List<SuperNode> path = planner.findPath(house);
                    Float cost = planner.findCost(house);
                }

            }
        }

        System.out.println("Welcome to the GPC power system!");
        System.out.println("Please select an option:");
        System.out.println("1. Change input data filename");
        System.out.println("2. Which houses are on?");
        System.out.println("3. Generator information");
        System.out.println("4. House to Generator path");
        System.out.println("5. Disable wire");
        System.out.println("6. Total revenue per hour");
        System.out.println("7. Revenue up to hour");

        Scanner userIn = new Scanner(System.in);
        int input = -1;
        boolean validInput = false;
        while (true) {
            while (!validInput) {
                try {
                    input = Integer.parseInt(userIn.nextLine());

                } catch (NumberFormatException e) {
                    System.out.println("Please enter valid integer option");
                    continue;
                }
            }
            switch (input) {
                case 1:
                    reader.promptForFileName();
                    break;
                case 2:
                    //TODO
                    break;
                case 3:
                    //TODO
                    break;
                case 4:
                    //TODO
                    break;
                case 5:
                    //TODO
                    break;
                case 6:
                    //TODO
                    break;
                case 7:
                    //TODO
                    break;
                default:
                    System.out.println("Not a valid option");


            }

        }
    }
}

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
        List<Generator> generatorList = new ArrayList<Generator>();
        for (Generator generator: generators.values()){
            generatorList.add(generator);
        }
        List<House> houseList = new ArrayList<House>();
        for (House house: houses.values()){
            houseList.add(house);
        }
        reader.readHousePower(houses);
        PathPlanner planner = new PathPlanner(generatorList, graph);
        MetaFunctions.printPath(planner.findPath(houseList.get(0)));
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
        int generator = 0;
        Integer hour = 0;
        while (true) {
            validInput = false;
            while (!validInput) {
                try {
                    input = Integer.parseInt(userIn.nextLine());
                    validInput = true;
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
                    System.out.println("Which hour?");

                    validInput = false;
                    while (!validInput) {
                        try {
                            hour = Integer.parseInt(userIn.nextLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter valid integer option");
                            continue;
                        }
                    }
                    List<House> onHouses = MetaFunctions.getOnHousesPerHour(houseList, hour);
                    for(House house: onHouses){
                        System.out.println(house);
                    }
                    break;
                case 3:
                    System.out.println("Which hour?");
                    validInput = false;
                    while (!validInput) {
                        try {
                            hour = Integer.parseInt(userIn.nextLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter valid integer option");
                            continue;
                        }
                    }
                    System.out.println("Which Generator?");
                    validInput = false;
                    while (!validInput) {
                        try {
                            generator = Integer.parseInt(userIn.nextLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter valid integer option");
                            continue;
                        }
                    }
                    Generator generator1 = generatorList.get(generator);

                    MetaFunctions.generatorInfoPerHour(generator1,planner,hour);
                    break;
                case 4:
                    System.out.println("Which hour?");
                    validInput = false;
                    while (!validInput) {
                        try {
                            hour = Integer.parseInt(userIn.nextLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter valid integer option");
                            continue;
                        }
                    }
                    System.out.println("Which House?");
                    validInput = false;
                    while (!validInput) {
                        try {
                            generator = Integer.parseInt(userIn.nextLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter valid integer option");
                            continue;
                        }
                    }
                    House house = houses.get(generator);
                    List<SuperNode> houses1 =  MetaFunctions.houseInfoPerHour(house,planner, hour);
                    MetaFunctions.printPath(houses1);
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

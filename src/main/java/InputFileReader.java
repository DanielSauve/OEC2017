import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Charberg on 1/21/2017.
 */
public class InputFileReader {

    private String fileName = "Test_Document.txt";
    private static final String genFile = "generators.txt";
    private static final String houseFile = "houses.txt";
    private static final String nodeFile = "nodes.txt";
    private static final String linkFile = "connect.txt";

    BufferedReader br;

    /**
     * Prompt the user for the test data title name
     */
    public void promptForFileName() {
        Scanner reader = new Scanner(System.in);
        boolean validFile = false;
        while(!validFile) {
            System.out.println("Please enter the filename");
            fileName = reader.nextLine();
            File f = new File(fileName);
            validFile = f.exists();
        }
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Read in link data from file, set parameter node links, and returns collection if Link instances
     * @param houses Hashmap of houses, where the key is the house id
     * @param nodes Hashmap of nodes, where the key is the string representation of the node
     * @param generators Hashmap of generators, where the key is the string representation of the generator
     * @return Collection of newly instantiated Link objects
     */
    public ArrayList<Link> readLink(HashMap<Integer, House> houses,
                                    HashMap<String, Node> nodes,
                                    HashMap<String, Generator> generators) {

        try {

            br = new BufferedReader(new FileReader(linkFile));

            String line;
            while((line = br.readLine()) != null) {

                if(line.length() == 0) {
                    continue;
                }

                //Use for CSVs, or any common delimiter
                String[] values = line.split("\\s+");

                //Do something with these values

                int index = 0;
                SuperNode sup1 = null;
                SuperNode sup2 = null;
                //If a house is defined as first link
                if(values[0].equals("H")) {
                    index++;
                    //Get the house
                    sup1 = houses.get(Integer.parseInt(values[index]));
                    index++;//If generator defined
                } else if(values[0].equals("G")) {
                    index++;
                    sup1 = generators.get(values[index]+values[index+1]);
                    index = index+2;
                }
                //If node defined
                else if(values[0].equals("N")) {
                    index++;
                    sup1 = nodes.get(values[index]+values[index+1]+values[index+2]);
                    index = index+3;
                }

                //Find second supernode in link
                if(values[index].equals("H")) {
                    index++;
                    //Get the house
                    sup2 = houses.get(Integer.parseInt(values[index]));
                    index++;//If generator defined
                } else if(values[index].equals("G")) {
                    index++;
                    sup2 = generators.get(values[index]+values[index+1]);
                    index = index+2;
                }
                //If node defined
                else if(values[index].equals("N")) {
                    index++;
                    sup2 = nodes.get(values[index]+values[index+1]+values[index+2]);
                    index = index+3;
                }

                if(sup1 == null || sup2 == null) {
                    System.out.println(line);
                    System.out.println("INVALID LINK DATA");
                    return null;
                }

                Link link = new Link(Float.parseFloat(values[index]));
                link.addNeighbour(sup1);
                link.addNeighbour(sup2);
                sup1.addLink(link);
                sup2.addLink(link);

            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception while reading file");
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Read in data from node config file
     * @return Collection of nodes representing input data
     */
    public HashMap<String,Node> readNode() {

        HashMap<String, Node> nodes = new HashMap<String, Node>();

        try {

            br = new BufferedReader(new FileReader(nodeFile));

            String line;
            while((line = br.readLine()) != null) {

                if(line.length() == 0) {
                    continue;
                }

                //Use for CSVs, or any common delimiter
                String[] values = line.split("\\s+");

                //Do something with these values

                Node node = new Node(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));

                nodes.put(values[0]+values[1]+values[2], node);

            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception while reading file");
            e.printStackTrace();
        }

        return nodes;

    }

    /**
     * Read in data from house config file
     * @return Collection of house objects representing input data
     */
    public HashMap<Integer,House> readHouse() {

        HashMap<Integer, House> houses = new HashMap<Integer, House>();

        try {

            br = new BufferedReader(new FileReader(houseFile));

            String line;
            while((line = br.readLine()) != null) {

                if(line.length() == 0) {
                    continue;
                }

                //Use for CSVs, or any common delimiter
                String[] values = line.split("\\s+");

                //Do something with these values

                House house = new House(Integer.parseInt(values[0]));

                houses.put(house.getId(), house);

            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception while reading file");
            e.printStackTrace();
        }

        return houses;

    }

    /**
     * Read in data from generators config file
     * @return Collection of generators representing input data
     */
    public HashMap<String,Generator> readGenerators() {

        HashMap<String, Generator> generators = new HashMap<String, Generator>();

        try {

            br = new BufferedReader(new FileReader(genFile));

            String line;
            while((line = br.readLine()) != null) {

                if(line.length() == 0) {
                    continue;
                }

                //Use for CSVs, or any common delimiter
                String[] values = line.split("\\s+");

                //Do something with these values

                Generator gen = new Generator(values[0], Integer.parseInt(values[1]));

                generators.put(gen.getCompany()+gen.getId(), gen);

            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception while reading file");
            e.printStackTrace();
        }

        return generators;

    }



    public void readHousePower(HashMap<Integer, House> houses) {

        try {

            br = new BufferedReader(new FileReader(fileName));

            String line;
            while((line = br.readLine()) != null) {

                if(line.length() == 0) {
                    continue;
                }

                //Use for CSVs, or any common delimiter
                String[] values = line.split("\\s+");

                //Do something with these values

                House h = houses.get(Integer.parseInt(values[1]));

                h.addOn(Integer.parseInt(values[0]), Integer.parseInt(values[2]));

            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception while reading file");
            e.printStackTrace();
        }

    }


    public void makeMap(ArrayList<SuperNode> superNodes,
                        ArrayList<Node> nodes,
                        ArrayList<Generator> generators,
                        ArrayList<House> houses,
                        ArrayList<Link> links) {



    }



}

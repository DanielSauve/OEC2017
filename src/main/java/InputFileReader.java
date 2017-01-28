import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Charberg on 1/21/2017.
 */
public class InputFileReader {

    //TODO: MAKE THIS CHANGEABLE BY THE USER
    private static final String fileName = "Test_Document.txt";

    BufferedReader br;

    public HashMap<Integer, House> read() {

        HashMap<Integer, House> houses = new HashMap<Integer, House>();

        try {

            br = new BufferedReader(new FileReader(fileName));

            String line;
            while((line = br.readLine()) != null) {

                if(line.length() == 0) {
                    continue;
                }

                //Use for CSVs, or any common delimiter
                System.out.println("Line: " + line);
                String[] values = line.split(",");

                //Do something with these values

                House house = new House(Integer.parseInt(values[1]));
                house.addOn(Integer.parseInt(values[0]), Integer.parseInt(values[0]));
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


}

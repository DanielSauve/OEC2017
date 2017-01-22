import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Charberg on 1/21/2017.
 */
public class InputFileReader {

    private static final String fileName = "example.txt";

    BufferedReader br;

    public void read() {
        try {

            br = new BufferedReader(new FileReader(fileName));

            String line;
            while((line = br.readLine()) != null) {

                //Use for CSVs, or any common delimiter
                System.out.println("Line: " + line);
                String[] values = line.split(",");

                //Do something with these values

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

}

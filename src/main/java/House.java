import java.util.HashMap;

/**
 * Created by puih123 on 2017-01-27.
 */
public class House extends SuperNode{

    private int id;

    private HashMap<Integer, Integer> powerHistory;

    public House(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void addOn(int hour, int on) {

        powerHistory.put(new Integer(hour), new Integer(on));

    }

    public int getOn(int hour){
        Integer val = powerHistory.get(hour);

        if(val == null) {   //If hour not specified, find most recent update. If not found, power is off

            while(hour > 0) {
                hour--;
                val = powerHistory.get(hour);
                if(val == null) {
                    continue;
                }
                return val.intValue();
            }
            //If reached this stage, value never set, so house never started using power
            return 0;

        }

        else {
            return val.intValue();
        }

    }

}

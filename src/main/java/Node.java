import java.util.ArrayList;

/**
 * Created by puih123 on 2017-01-27.
 */
public class Node extends SuperNode{

    private String company;
    private int genID;
    private int id;

    public Node(String company, int genID, int id) {
        this.company = company;
        this.genID = genID;
        this.id = id;
        this.links = new ArrayList<Link>();
    }

    public String toString() {
        return "N: " + this.company+":"+this.genID+":"+this.id;
    }

    public boolean equals(Object o) {

        if (!(o instanceof Node)) {
            return false;
        }

        Node n = (Node) o;

        return(n.getCompany().equals(this.getCompany())
            && n.getGenID() == this.getGenID()
            && n.getId() == this.getId());

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getGenID() {
        return genID;
    }

    public void setGenID(int genID) {
        this.genID = genID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

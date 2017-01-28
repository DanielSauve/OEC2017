/**
 * Created by puih123 on 2017-01-27.
 */
public class Generator extends SuperNode{

    String company;
    int id;

    public Generator(String company, int id) {
        this.company = company;
        this.id = id;
    }

    public boolean equals(Object o) {

        if (!(o instanceof Generator)) {
            return false;
        }

        Generator g = (Generator) o;

        return (g.getCompany().equals(this.getCompany()) && g.getId() == this.getId());

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

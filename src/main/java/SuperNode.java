import java.util.List;

/**
 * Created by danielsauve on 2017-01-27.
 */
public abstract class SuperNode {
    protected List<Link> links;

    public void addLink(Link link){
        links.add(link);
    }

    public List<Link> getLinks(){
        return links;
    }
}

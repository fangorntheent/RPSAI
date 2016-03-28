import java.util.ArrayList;

/**
 * Created by Gus Lipkin on 3/27/2016.
 */
public class AlgGeneral {

    public ArrayList history;
    public ArrayList winHistory;
    public ArrayList algResults;

    public AlgGeneral() {
        history = new ArrayList<Integer>();
        winHistory = new ArrayList<Integer>();
        algResults = new ArrayList<Integer>();

        int x = 0;
        while (x < 5) {
            algResults.add(-1);
            x += 1;
        }
    }
}

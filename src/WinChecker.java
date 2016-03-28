import java.util.ArrayList;

/**
 * Created by Gus Lipkin on 3/27/2016.
 */
public class WinChecker {

    public String winnerText;
    public int winnerInt;

    public WinChecker() {
        winnerInt = -1;
        winnerText = "error";
    }

    public WinChecker(int player, int ai) {
        if ((player - ai == 1) || (player - ai == -2)) {
            winnerInt = 1;
            winnerText = "tie";
        }
        else if ((player - ai == -1) || (player - ai == 2)) {
            winnerInt = 1;
            winnerText = "tie";
        }
        else {
            winnerInt = 1;
            winnerText = "tie";
        }
    }

    public WinChecker(int player, int ai, ArrayList<Integer> history) {
        if ((player - ai == 1) || (player - ai == -2)) {
            winnerInt = 0;
            winnerText =  "player";
            history.add(winnerInt);
        }
        else if ((player - ai == -1) || (player - ai == 2)) {
            winnerInt = 2;
            winnerText = "ai";
            history.add(winnerInt);
        }
        else {
            winnerInt = 1;
            winnerText = "tie";
            history.add(winnerInt);
        }
    }
}

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gus Lipkin on 3/29/2016.
 */
public class AlgFive {

    // How valuable Alg results are
    public int total;
    // How often Alg is correct
    public int weight;
    // History of Alg's throws
    public ArrayList history;
    // History of Alg's success or lack thereof
    public ArrayList winHistory;

    private int r = 0;
    private int p = 0;
    private int s = 0;

    // Constructor
    public AlgFive() {

        total = 0;
        weight = 1;
        history = new ArrayList<Integer>();
        winHistory = new ArrayList<Integer>();
    }

    // Match < 2
    // Add and return Alg's throw
    public int getAlgFive(AlgGeneral algGeneral) {

        algGeneral.algResults.set(2, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(2));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlgFive(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        int containsPattern = 1;
        int r = 0;
        int p = 0;
        int s = 0;
        int option = 0;
        String pattern = "";
        String playerHistory = playerGeneral.history.toString().substring(1, playerGeneral.history.size() - 1).replaceAll(", ", "");
        ArrayList indexOfPattern = new ArrayList<Integer>();

        WinningPlay winningPlay = new WinningPlay();

        // Needs extra time to kick in but must maintain same standards
        if (playerGeneral.history.size() < 4)
            algGeneral.algResults.set(4, getAlgFive(algGeneral));

        for (int i = 0; i < 4; i++)
            pattern = pattern + (Integer)(playerGeneral.history.get(playerGeneral.history.size() - i - 1));

        while (playerHistory.contains(pattern)) {
                indexOfPattern.add(playerHistory.indexOf(pattern));
                playerHistory = playerHistory.substring(0, playerHistory.indexOf(pattern)) + "----" + playerHistory.substring(playerHistory.indexOf(pattern));
        }

        // Counts how many time the fifth throw is each type of throw
        if (indexOfPattern.size() > 0) {
            for (int i = 0; i < indexOfPattern.size() - 1; i++)
                option = (Integer)(playerGeneral.history.get((Integer)(indexOfPattern.get(i)) + 4));
                if (option == 0)
                    r++;
                else if (option == 1)
                    p++;
                else if (option == 2)
                    s++;
        }

        if ((r > p) && (r > s))
            winningPlay.getWinningPlay(r);
        else if ((p > r) && (p > s))
            winningPlay.getWinningPlay(p);
        else if ((s > r) && (s > p))
            winningPlay.getWinningPlay(s);
        else
            winningPlay.getWinningPlay(new Random().nextInt(2));

        algGeneral.algResults.set(4, winningPlay.winningPlay);
        history.add(winningPlay.winningPlay);
        return (Integer)(history.get(history.size() - 1));
    }
}

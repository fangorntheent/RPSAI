import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gus Lipkin on 3/29/2016.
 */
public class AlgFive {

    // Alg number
    public int algNumber;
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

        algNumber = 4;
        total = 0;
        weight = 1;
        history = new ArrayList<>();
        winHistory = new ArrayList<>();
    }

    // Add and return Alg's throw
    public int getAlgFive(AlgGeneral algGeneral) {

        algGeneral.algResults.set(algNumber, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(algNumber));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlgFive(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        // Needs extra time to kick in but must maintain same standards
        if (playerGeneral.history.size() < 4) {
            algGeneral.algResults.set(algNumber, getAlgFive(algGeneral));
            return (Integer) (history.get(history.size() - 1));
        }

        int r = 0;
        int p = 0;
        int s = 0;
        int option = 0;
        String pattern = "";
        String playerHistory = playerGeneral.history.toString().substring(1, playerGeneral.history.size() - 1).replaceAll(", ", "");
        ArrayList indexOfPattern = new ArrayList<>();

        WinningPlay winningPlay = new WinningPlay();

        for (int i = 0; i < 4; i++)
            pattern = pattern + playerGeneral.history.get(playerGeneral.history.size() - i - 1);

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

        winningPlay.setWinningPlay(algGeneral.winningPlaySeeder(r, p, s));

        algGeneral.algResults.set(algNumber, winningPlay.winningPlay);
        history.add(winningPlay.winningPlay);
        return (Integer)(history.get(history.size() - 1));
    }
}

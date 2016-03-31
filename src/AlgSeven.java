import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gus Lipkin on 3/30/2016.
 */
public class AlgSeven {

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
    public AlgSeven() {

        algNumber = 6;
        total = 0;
        weight = 1;
        history = new ArrayList<>();
        winHistory = new ArrayList<>();
    }

    // Add and return Alg's throw
    public int getAlgSeven(AlgGeneral algGeneral) {

        algGeneral.algResults.set(algNumber, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(algNumber));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlgSeven(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        // Needs extra time to kick in but must maintain same standards
        if (playerGeneral.history.size() < 3)
            algGeneral.algResults.set(algNumber, getAlgSeven(algGeneral));

        WinningPlay winningPlay = new WinningPlay();

        playerGeneral.setThrowCount(playerGeneral.history.size() - 1);
        winningPlay.setWinningPlay(algGeneral.winningPlaySeeder(playerGeneral.rCount, playerGeneral.pCount, playerGeneral.sCount));

        algGeneral.algResults.set(algNumber, winningPlay.winningPlay);
        history.add(algGeneral.algResults.get(algNumber));
        return (Integer)(history.get(history.size() - 1));
    }

    // Returns total
    public int getTotal() {
        return total;
    }

    // Returns weight
    public int getWeight() {
        return weight;
    }

    // Returns history
    public ArrayList<Integer> getHistory() {
        return history;
    }

    // Returns winHistory
    public ArrayList<Integer> getWinHistory() {
        return winHistory;
    }

    // Sets total to default
    public void setTotal() {
        total = 0;
    }

    // Sets total to value
    public void setTotal(int value) {
        total = value;
    }

    // Sets weight to default
    public void setWeight() {
        weight = 0;
    }

    // Sets weight to value
    public void setWeight(int value) {
        weight = value;
    }
}
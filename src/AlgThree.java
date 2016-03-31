import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gus Lipkin on 3/28/2016.
 *
 * ALGORITHM PURPOSE
 *  Seed the most used throw by player and return the winning play
 * ALGORITHM SUMMARY
 *  See purpose
 */
public class AlgThree {

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
    public AlgThree() {

        algNumber = 2;
        total = 0;
        weight = 1;
        history = new ArrayList<>();
        winHistory = new ArrayList<>();
    }

    // Add and return Alg's throw
    public int getAlgThree(AlgGeneral algGeneral) {

        algGeneral.algResults.set(algNumber, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(algNumber));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlgThree(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        // Needs extra time to kick in but must maintain same standards
        if (playerGeneral.history.size() < 3)
            algGeneral.algResults.set(algNumber, getAlgThree(algGeneral));

        WinningPlay winningPlay = new WinningPlay();

        getThrowCount(2, playerGeneral);
        winningPlay.setWinningPlay(algGeneral.winningPlaySeeder(r, p, s));

        algGeneral.algResults.set(algNumber, winningPlay.winningPlay);
        history.add(winningPlay.winningPlay);
        return (Integer)(history.get(history.size() - 1));
    }

    private void getThrowCount(int range, PlayerGeneral playerGeneral) {

        for (int i = 0; i <= range; i++) {
            if ((Integer)(playerGeneral.history.get(i)) == 0)
                r++;
            else if ((Integer)(playerGeneral.history.get(i)) == 1)
                p++;
            else if ((Integer)(playerGeneral.history.get(i)) == 2)
                s++;
        }

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

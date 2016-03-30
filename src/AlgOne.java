import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gus Lipkin on 3/27/2016.
 *
 * ALGORITHM PURPOSE
 *  Backbone of ai
 * ALGORITHM SUMMARY
 *  if player won, repeat play
 *  if ai won, play player's most recent play
 *  if tie, play what would have lost to player's most recent play
 */
public class AlgOne {

    // How valuable Alg results are
    public int total;
    // How often Alg is correct
    public int weight;
    // History of Alg's throws
    public ArrayList history;
    // History of Alg's success or lack thereof
    public ArrayList winHistory;

    // Constructor
    public AlgOne() {

        total = 0;
        weight = 1;
        history = new ArrayList<>();
        winHistory = new ArrayList<>();
    }

    // Add and return Alg's throw
    public int getAlgOne(AlgGeneral algGeneral) {

        algGeneral.algResults.set(0, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(0));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlgOne(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        if (playerGeneral.history.size() < 2) {
            algGeneral.algResults.set(0, getAlgOne(algGeneral));
            return (Integer) (history.get(history.size() - 1));
        }

        int algPrev = (Integer)(algGeneral.history.get(algGeneral.history.size() - 1));
        int playerPrev = (Integer)(playerGeneral.history.get(playerGeneral.history.size() - 1));

        WinChecker winChecker = new WinChecker(playerPrev, algPrev);
        WinningPlay winningPlay = new WinningPlay(playerPrev);

        if (winChecker.winnerText == "player")
            algGeneral.algResults.set(0, algPrev);
        else if (winChecker.winnerText == "ai")
            algGeneral.algResults.set(0, playerPrev);
        else if (winChecker.winnerText == "tie")
            if (playerPrev == 0)
                algGeneral.algResults.set(0, winningPlay.losingPlay);
            else if (playerPrev == 1)
                algGeneral.algResults.set(0, winningPlay.losingPlay);
            else if (playerPrev == 2)
                algGeneral.algResults.set(0, winningPlay.losingPlay);

        history.add(algGeneral.algResults.get(0));
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
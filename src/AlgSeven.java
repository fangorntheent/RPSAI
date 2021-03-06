import java.util.ArrayList;
import java.util.Random;

public class AlgSeven implements AlgInterface {

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
        total = 1;
        weight = 1;
        history = new ArrayList<>();
        winHistory = new ArrayList<>();
    }

    // Add and return Alg's throw
    public int getAlg(AlgGeneral algGeneral) {

        algGeneral.algResults.set(algNumber, new Random().nextInt(3));
        history.add(algGeneral.algResults.get(algNumber));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlg(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        // Needs extra time to kick in but must maintain same standards
        if (algGeneral.matchNumber < 3)
            return getAlg(algGeneral);

        int winningPlay;

        playerGeneral.setThrowCount(playerGeneral.history.size() - 1);
        winningPlay = algGeneral.winningPlaySeeder(playerGeneral.rCount, playerGeneral.pCount, playerGeneral.sCount);

        algGeneral.algResults.set(algNumber, winningPlay);
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
        total = 1;
    }

    // Sets total to value
    public void setTotal(int value) {
        total = value;
    }

    // Sets weight to default
    public void setWeight() {
        weight = 1;
    }

    // Sets weight to value
    public void setWeight(int value) {
        weight = value;
    }
}


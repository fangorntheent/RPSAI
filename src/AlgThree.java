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
        total = 0;
        weight = 1;
        history = new ArrayList<Integer>();
        winHistory = new ArrayList<Integer>();
    }

    // Match < 2
    // Add and return Alg's throw
    public int getAlgThree(AlgGeneral algGeneral) {
        algGeneral.algResults.set(2, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(2));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlgThree(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        // Needs extra time to kick in but must maintain same standards
        if (playerGeneral.history.size() < 4) {
            algGeneral.algResults.set(2, getAlgThree(algGeneral));
        }



        history.add(algGeneral.algResults.get(2));
        return (Integer)(history.get(history.size() - 1));
    }

    public void getThrowCount(int range, PlayerGeneral playerGeneral) {
        for (int i = 0; i <= range; i++) {
            if ((Integer)(playerGeneral.history.get(i)) == 0)
                r++;
            else if ((Integer)(playerGeneral.history.get(i)) == 1)
                p++;
            else if ((Integer)(playerGeneral.history.get(i)) == 2)
                s++;
        }

    }

    public int beatsMostUsed(int range, PlayerGeneral playerGeneral) {

        WinningPlay winningPlay = new WinningPlay();
        getThrowCount(range, playerGeneral);

        if ((r > p) && (r > s))
            winningPlay.getWinningPlay(r);
        else if ((p > r) && (p > s))
            winningPlay.getWinningPlay(p);
        else if ((s > r) && (s > p))
            winningPlay.getWinningPlay(s);
        else
            winningPlay.getWinningPlay(new Random().nextInt(2));

        history.add(winningPlay.winningPlay);
        return (Integer)(history.get(history.size() - 1));
    }

    public int beatsLeastUsed(int range, PlayerGeneral playerGeneral) {

        WinningPlay winningPlay = new WinningPlay();
        getThrowCount(range, playerGeneral);

        if ((r < p) && (r < s))
            winningPlay.getWinningPlay(r);
        else if ((p < r) && (p < s))
            winningPlay.getWinningPlay(p);
        else if ((s < r) && (s < p))
            winningPlay.getWinningPlay(s);
        else
            winningPlay.getWinningPlay(new Random().nextInt(2));

        history.add(winningPlay.winningPlay);
        return (Integer)(history.get(history.size() - 1));
    }
}

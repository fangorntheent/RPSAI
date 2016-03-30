import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gus Lipkin on 3/28/2016.
 *
 * ALGORITHM PURPOSE
 *  Detect the likelihood of player repeating a winning move
 * ALGORITHM SUMMARY
 *  if ai lost, calculate winning play and adjust weight as necessary
 */
public class AlgFour {

    // How valuable Alg results are
    public int total;
    // How often Alg is correct
    public int weight;
    // How many times in a row player has repeated a move
    public int repeat;
    // History of Alg's throws
    public ArrayList history;
    // History of Alg's success or lack thereof
    public ArrayList winHistory;

    // Constructor
    public AlgFour() {
        total = 0;
        weight = 1;
        repeat = 1;
        history = new ArrayList<>();
        winHistory = new ArrayList<>();
    }

    // Add and return Alg's throw
    public int getAlgFour(AlgGeneral algGeneral) {

        algGeneral.algResults.set(3, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(3));
        return (Integer)(history.get(history.size() - 1));
    }

    public int getAlgFour(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        if (playerGeneral.history.size() < 2) {
            algGeneral.algResults.set(3, getAlgFour(algGeneral));
            return (Integer) (history.get(history.size() - 1));
        }

        int playerPrev = (Integer)(algGeneral.winHistory.get(algGeneral.winHistory.size() - 1));
        int playerPrevPrev = (Integer)(algGeneral.winHistory.get(algGeneral.winHistory.size() - 2));
        int winPrev = (Integer)(algGeneral.winHistory.get(algGeneral.winHistory.size() - 1));
        int winPrevPrev = (Integer)(algGeneral.winHistory.get(algGeneral.winHistory.size() - 2));

        WinningPlay winningPlay = new WinningPlay(playerPrev);

        if (winPrev == 0) {
            algGeneral.algResults.set(3, winningPlay.winningPlay);

            if ((playerPrev == playerPrevPrev) && (winPrevPrev == 0))
                repeat += 3;

            repeat += 2;
        }
        else
            repeat -= 1;

        history.add(algGeneral.algResults.get(3));
        return (Integer)(history.get(history.size() - 1));
    }
}

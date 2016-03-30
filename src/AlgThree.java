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
        history = new ArrayList<>();
        winHistory = new ArrayList<>();
    }

    // Add and return Alg's throw
    public int getAlgThree(AlgGeneral algGeneral) {

        algGeneral.algResults.set(2, new Random().nextInt(2));
        history.add(algGeneral.algResults.get(2));
        return (Integer)(history.get(history.size() - 1));
    }

    // Add and return Alg's throw
    public int getAlgThree(PlayerGeneral playerGeneral, AlgGeneral algGeneral) {

        // Needs extra time to kick in but must maintain same standards
        if (playerGeneral.history.size() < 4)
            algGeneral.algResults.set(2, getAlgThree(algGeneral));

        // Code that needs to be converted from python
        /**
        def master_ai():
        #manage alg 1
            print('---START SELECTION PROCESS---')
        alg_1_history.append(alg_1())
        global alg_1_score
        if round >= 3:
            print("  ALG 1 selected " + str(num_to_words[alg_1_history[round]].lower()))
          if check_winner(player_input,alg_1_history[round]) == 'player':
              alg_1_score -= 1
          if check_winner(player_input, alg_1_history[round]) == 'ai':
              alg_1_score += 1
          print("  ALG 1 score = " + str(alg_1_score))

      #manage alg 2
      alg_2_history.append(alg_2())
      global alg_2_score
      if round >= 4:
          print("  ALG 2 selected " + str(num_to_words[alg_2_history[round]].lower()) + ' because your last three throws were:' + str(player_history[round - 3:round]))
          if check_winner(player_input, alg_2_history[round]) == 'player':
               alg_2_score -= 1
           if check_winner(player_input, alg_2_history[round]) == 'ai':
               alg_2_score += 1
           print("  ALG 2 score = " + str(alg_2_score))

       #manage alg 3
      alg_3_history.append(alg_3())
      global alg_3_score
      if round >= 4:
        print("  ALG 3 selected " + str(
            num_to_words[alg_3_history[round]].lower()) + ' because your last three throws were:' + str(
            player_history[round - 3:round]))
        if check_winner(player_input, alg_3_history[round]) == 'player':
            alg_3_score -= 1
        if check_winner(player_input, alg_3_history[round]) == 'ai':
            alg_3_score += 1
        print("  ALG 3 score = " + str(alg_3_score))

        print('---END SELECTION PROCESS---')
       if alg_1_score >= alg_2_score and alg_1_score >= alg_3_score:
         print('ALG 1 selected')
          return alg_1_history[round]
       if alg_2_score >= alg_3_score and alg_2_score >= alg_1_score:
           print('ALG 2 selected')
           return alg_2_history[round]
        if alg_3_score >= alg_2_score and alg_3_score >= alg_1_score:
           print('ALG 3 selected')
          return alg_3_history[round]

         */

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

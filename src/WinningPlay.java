/**
 * Created by Gus Lipkin on 3/27/2016.
 */
public class WinningPlay {

    public int winningPlay;
    public int losingPlay;
    public int samePlay;

    public WinningPlay() {
        winningPlay = -1;
        losingPlay = -1;
        samePlay = -1;
    }

    public WinningPlay(int player) {

        getWinningPlay(player);
    }

    public void getWinningPlay(int player) {
        if (player == 0) {
            winningPlay = 1;
            losingPlay = 2;
            samePlay = 0;
        }
        else if (player == 1) {
            winningPlay = 2;
            losingPlay = 0;
            samePlay = 1;
        }
        else if (player == 2) {
            winningPlay = 0;
            losingPlay = 1;
            samePlay = 2;
        }
    }
}

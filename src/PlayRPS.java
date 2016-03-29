/**
 * Created by Gus Lipkin on 3/28/2016.
 */
public class PlayRPS {

    private Translator translator = new Translator();

    private void printWinner(int playerPrev, int algPrev) {

        WinChecker winChecker = new WinChecker(playerPrev, algPrev);

        if (winChecker.winnerInt == 0)
            System.out.println("Your " + translator.numToWords(playerPrev) + " BEATS " + translator.numToWords(algPrev));
        else if (winChecker.winnerInt == 1)
            System.out.println("Your " + translator.numToWords(playerPrev) + " TIES WITH " + translator.numToWords(algPrev));
        else if (winChecker.winnerInt == 2)
            System.out.println("Your " + translator.numToWords(playerPrev) + " LOSES TO " + translator.numToWords(algPrev));
        else
            System.out.println("Oops. I farted.");
    }

    public static void main(String[] args) {
        
        AlgGeneral algGeneral = new AlgGeneral();
        PlayerGeneral playerGeneral = new PlayerGeneral();
        AlgOne algOne = new AlgOne();

        algGeneral.algResults.set(0, 1);
        System.out.println(algGeneral.algResults);
        System.out.println(algOne.getAlgOne(algGeneral));
        System.out.println(algGeneral.algResults);
        System.out.println("hi");
        String s = algGeneral.algResults.toString();
        s = s.replaceAll(", ", "");
        s = s.substring(1, s.length() - 1);
        System.out.println(s);
    }
}

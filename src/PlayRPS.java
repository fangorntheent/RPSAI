import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gus Lipkin on 3/28/2016.
 */
public class PlayRPS {

    private static Translator translator = new Translator();
    private static int matchNumber = 0;

    private static AlgGeneral algGeneral;
    private static PlayerGeneral playerGeneral;
    private static WinChecker winChecker;

    private static void printWinner(int playerPrev, int algPrev) {

        WinChecker winChecker = new WinChecker();
        winChecker.setWinner(playerPrev, algPrev);

        if (winChecker.winnerInt == 0)
            System.out.println("Your " + translator.numToWords(playerPrev) + " BEATS " + translator.numToWords(algPrev));
        else if (winChecker.winnerInt == 1)
            System.out.println("Your " + translator.numToWords(playerPrev) + " TIES WITH " + translator.numToWords(algPrev));
        else if (winChecker.winnerInt == 2)
            System.out.println("Your " + translator.numToWords(playerPrev) + " LOSES TO " + translator.numToWords(algPrev));
        else
            System.out.println("Oops. I farted.");
    }

    private static int combineAlgs(ArrayList<AlgInterface> algs) {

        int algIndex = 0;

        for (int i = 0; i < algs.size(); i++)
            algs.get(i).getAlg(playerGeneral, algGeneral);

        for (int i = 0; i < matchNumber; i++)
            algs.get(i).setTotal(algs.get(i).getTotal() * algs.get(i).getWeight());
        for (int i = 0; i <= 2; i++)
            algs.get(i).setTotal(algs.get(i).getTotal() * algs.get(i).getWeight() * 3);
        for (int i = 3; i <= 4; i++)
            algs.get(i).setTotal(algs.get(i).getTotal() * algs.get(i).getWeight() * 2);

        for (int i = 1; i < algGeneral.algResults.size(); i++) {
            algGeneral.algResults.set(i, algs.get(i).getTotal());
            if ((Integer)(algGeneral.algResults.get(i)) > (Integer)(algGeneral.algResults.get(i - 1)))
                algIndex = i;
        }

        algGeneral.algResults.set(0, algIndex);
        return algIndex;
    }

    private static int runChosenAlg(ArrayList<AlgInterface> algs) {

        int algPrev = algs.get(algGeneral.chosenAlgNumber).getHistory().get(algs.get(algGeneral.chosenAlgNumber).getHistory().size() - 1);
        winChecker.addWinner((Integer)(playerGeneral.history.get(playerGeneral.history.size() - 1)), algPrev, algGeneral.winHistory);
        System.out.println(algPrev);
        return algPrev;
    }

    private static void setWeight(ArrayList<AlgInterface> algs) {

        for (int i = 0; i < algs.size(); i++) {
            algs.get(i).setWeight(algs.get(i).getWeight() + 1);
        }
    }

    public static void main(String[] args) {

        algGeneral = new AlgGeneral();
        playerGeneral = new PlayerGeneral();
        winChecker = new WinChecker();
        for (int i = 0; i < 2; i++) {
            playerGeneral.history.add(new Random().nextInt(3));
            algGeneral.history.add(new Random().nextInt(3));
            algGeneral.winHistory.add(new Random().nextInt(3));
        }

        AlgInterface algOne = new AlgOne();
        AlgInterface algTwo = new AlgTwo();
        AlgInterface algThree = new AlgThree();
        AlgInterface algFour = new AlgFour();
        AlgInterface algFive = new AlgFive();
        AlgInterface algSix = new AlgSix();
        AlgInterface algSeven = new AlgSeven();

        ArrayList algList = new ArrayList<AlgInterface>();

        algList.add(algOne);
        algList.add(algTwo);
        algList.add(algThree);
        algList.add(algFour);
        algList.add(algFive);
        algList.add(algSix);
        algList.add(algSeven);

        int alg = 0;

        for (int i = 0; i < 10; i++) {
            playerGeneral.history.add(new Random().nextInt(3));
            setWeight(algList);
            algGeneral.chosenAlgNumber = combineAlgs(algList);
            runChosenAlg(algList);
            printWinner((Integer)(playerGeneral.history.get(playerGeneral.history.size() - 1)), (Integer)(algGeneral.history.get(algGeneral.history.size() - 1)));
            matchNumber++;
        }
        /*algGeneral.algResults.set(0, algGeneral.totalAlgNumber + 1);
        String s = algGeneral.algResults.toString();
        s = s.replaceAll(", ", "");
        s = s.substring(1, s.length() - 1);
        System.out.println(s);
        */
    }
}

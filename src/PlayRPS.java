import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

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

    private static int combineAlgs(ArrayList algs) {
        //System.out.println(algs.get(0).getClass().getDeclaredMethods()[0]);
        //System.out.println(algs.get(0).getClass().getDeclaredMethod("getTotal"));
        System.out.println(algs.get(0).getClass().getDeclaredFields()[0]);
        try {
            System.out.println(algs.get(0).getClass().getDeclaredField("algNumber").getInt(algs.get(0)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("oops");
        return 0;
    }

    private static void setWeight(ArrayList algs) {

        for (int i = 0; i <= algs.size() - 1; i++) {
            try {
                Field field = algs.get(i).getClass().getDeclaredField("weight");
                Method method = algs.get(i).getClass().getDeclaredMethod("setWeight", new Class<?>[]{int.class});
                Object weight = field.get(algs.get(i));
                Object setWeight = method.invoke(algs.get(i), (Integer)(2) + (Integer)(weight));
                weight = field.get(algs.get(i));
                System.out.println(weight);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

        AlgGeneral algGeneral = new AlgGeneral();
        PlayerGeneral playerGeneral = new PlayerGeneral();

        AlgOne algOne = new AlgOne();
        AlgTwo algTwo = new AlgTwo();
        AlgThree algThree = new AlgThree();
        AlgFour algFour = new AlgFour();

        ArrayList algList = new ArrayList<>();

        algList.add(algOne);
        algList.add(algTwo);
        algList.add(algThree);
        algList.add(algFour);

        setWeight(algList);

        algGeneral.algResults.set(0, 1);
        String s = algGeneral.algResults.toString();
        s = s.replaceAll(", ", "");
        s = s.substring(1, s.length() - 1);
        System.out.println(s);
    }
}

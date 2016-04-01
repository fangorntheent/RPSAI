import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Gus Lipkin on 3/28/2016.
 */
public class PlayRPS {

    private Translator translator = new Translator();
    private static int matchNumber = 0;

    private static AlgGeneral algGeneral;
    private static PlayerGeneral playerGeneral;

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

        int algIndex = 0;
        Method method;

        for (int i = 0; i < algs.size(); i++) {
            try {
                method = algs.get(i).getClass().getDeclaredMethod("getAlg", new Class<?>[]{playerGeneral.getClass(), algGeneral.getClass()});
                method.invoke(algs.get(i), playerGeneral, algGeneral);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i <= matchNumber; i++)
            setTotal(algs, 1);
        for (int i = 0; i <= 2; i++)
            setTotal(algs, 3);
        for (int i = 3; i <= 4; i++)
            setTotal(algs, 2);

        for (int i = 1; i < algGeneral.algResults.size(); i++) {
            algGeneral.algResults.set(i, getTotal(algs, i));
            if ((Integer)(algGeneral.algResults.get(i)) > (Integer)(algGeneral.algResults.get(i - 1)))
                algIndex = i;
        }

        algGeneral.algResults.set(0, algIndex);
        return algIndex - 1;
    }

    private static int runChosenAlg(ArrayList algs, int chosenAlgNumber) {

        Method getAlgMethod;

        try {
            getAlgMethod = algs.get(chosenAlgNumber).getClass().getDeclaredMethod("getAlg", new Class<?>[]{playerGeneral.getClass(), algGeneral.getClass()});
            return (int) getAlgMethod.invoke(algs.get(chosenAlgNumber), playerGeneral, algGeneral);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void setTotal(ArrayList algs, int weight) {

        Field totalField;
        Method setTotal;
        Object totalObject;

        for (int i = 0; i < algs.size(); i++) {
            try {
                totalField = algs.get(i).getClass().getDeclaredField("total");
                setTotal = algs.get(i).getClass().getDeclaredMethod("setTotal", new Class<?>[]{int.class});
                totalObject = totalField.get(algs.get(i));
                setTotal.invoke(algs.get(i), (Integer)(totalObject) * getWeight(algs, i) * weight);
            } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getTotal(ArrayList algs, int i) {

        Method getTotalMethod;

        try {
            getTotalMethod = algs.get(i).getClass().getDeclaredMethod("getWeight");
            return (int) getTotalMethod.invoke(algs.get(i));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private static void setWeight(ArrayList algs) {

        Field weightField;
        Method setWeightMethod;
        Object weightObject;

        for (int i = 0; i < algs.size(); i++) {
            try {
                weightField = algs.get(i).getClass().getDeclaredField("weight");
                setWeightMethod = algs.get(i).getClass().getDeclaredMethod("setWeight", new Class<?>[]{int.class});
                weightObject = weightField.get(algs.get(i));
                setWeightMethod.invoke(algs.get(i), (Integer)(weightObject) + (Integer)(1));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getWeight(ArrayList algs, int i) {

        Method getWeightMethod;

            try {
                getWeightMethod = algs.get(i).getClass().getDeclaredMethod("getWeight");
                return (int) getWeightMethod.invoke(algs.get(i));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        return 1;
    }

    public static void main(String[] args) {

        algGeneral = new AlgGeneral();
        playerGeneral = new PlayerGeneral();
        playerGeneral.history.add(0);

        AlgOne algOne = new AlgOne();
        AlgTwo algTwo = new AlgTwo();
        AlgThree algThree = new AlgThree();
        AlgFour algFour = new AlgFour();
        AlgFive algFive = new AlgFive();
        AlgSix algSix = new AlgSix();
        AlgSeven algSeven = new AlgSeven();

        ArrayList algList = new ArrayList<>();

        algList.add(algOne);
        algList.add(algTwo);
        algList.add(algThree);
        algList.add(algFour);
        algList.add(algFive);
        algList.add(algSix);
        algList.add(algSeven);

        setWeight(algList);
        int alg = combineAlgs(algList);

        algGeneral.algResults.set(0, algGeneral.totalAlgNumber + 1);
        String s = algGeneral.algResults.toString();
        s = s.replaceAll(", ", "");
        s = s.substring(1, s.length() - 1);
        System.out.println(s);
    }
}

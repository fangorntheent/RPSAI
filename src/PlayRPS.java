/**
 * Created by Gus Lipkin on 3/28/2016.
 */
public class PlayRPS {

    public static void main(String[] args) {
        AlgGeneral algGeneral = new AlgGeneral();
        PlayerGeneral playerGeneral = new PlayerGeneral();
        AlgOne algOne = new AlgOne();

        algGeneral.algResults.set(0, 1);
        System.out.println(algGeneral.algResults);
        System.out.println(algOne.getAlgOne(algGeneral));
        System.out.println(algGeneral.algResults);
    }
}

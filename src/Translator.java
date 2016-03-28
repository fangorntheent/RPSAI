/**
 * Created by Gus Lipkin on 3/27/2016.
 */
public class Translator {

    public int wordsToNum;
    public String numToWords;

    public Translator() {
        wordsToNum = -1;
        numToWords = "Error";
    }

    public Translator(String input) {
        switch (input) {
            case "r":
                wordsToNum = 0;
                break;
            case "p":
                wordsToNum = 1;
                break;
            case "s":
                wordsToNum = 2;
                break;
            case "rock":
                wordsToNum = 0;
                break;
            case "paper":
                wordsToNum = 1;
                break;
            case "scissors":
                wordsToNum = 2;
                break;
            case "Rock":
                wordsToNum = 0;
                break;
            case "Paper":
                wordsToNum = 1;
                break;
            case "Scissors":
                wordsToNum = 2;
                break;
            default:
                wordsToNum = -1;
                break;
        }
    }

    public Translator(int input) {
        switch (input) {
            case 0:
                numToWords = "Rock";
                break;
            case 1:
                numToWords = "Paper";
                break;
            case 2:
                numToWords = "Scissors";
                break;
            default:
                numToWords = "Error";
                break;
        }
    }
}

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Main {
    // Declares a quotes class with SPY 1min data as the input
    // Creates several ArrayLists containing technical indicators
    // Prints all ArrayLists line-by-line, preceded by the day, time, and closing price.
    public static void main(String[] args){
        Quotes SPY_1min = new Quotes("SPY", "1min");
        ArrayList<Double> Change = SPY_1min.Change(1);
        ArrayList<Double> ROC = SPY_1min.ROC(12);
        ArrayList<Double> EMA = SPY_1min.EMA(14, 10);
        ArrayList<Double> MACD = SPY_1min.MACD(12, 26, 10);
        ArrayList<Double> RSI = SPY_1min.RSI(14);
        for (int c=0; c<SPY_1min.length-26; c++){
            DecimalFormat f = new DecimalFormat("###.###"); // to prevent run-on doubles with dozens of digits after the decimal
            System.out.print(f.format(SPY_1min.month.get(c)) + "/" + f.format(SPY_1min.day.get(c)) + "/" + f.format(SPY_1min.year.get(c)) + " ");
            System.out.print(f.format(SPY_1min.hour.get(c)) + ":" + SPY_1min.minute.get(c) + " ");
            System.out.print(f.format(SPY_1min.close.get(c)) + " " + f.format(Change.get(c)) + " " + f.format(ROC.get(c)));
            System.out.println(f.format(EMA.get(c)) + " " + f.format(MACD.get(c)) + " " + f.format(RSI.get(c)));
        }
    }
}

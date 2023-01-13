import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

// This program reads quotes data for a financial security from a .txt file and outputs a cleanly formatted version of the dates/times, closing prices, change, and several technical indicators, line-by-line.

// SPY and FAKE are the two symbols whose data are available in the "Data Files" folder within this repository. SPY is an S&P500 ETF; FAKE is a randomly generated motion over many hypothetical trading periods, created by this program's complementary python component.
// The purpose is to illustrate the similarity in the movement of the stock market as a whole and a completely randomly generated motion, especially the technical indicators applied to them.

// Attempting to draw immediate conclusions about the next movement of the market based on the values of oscillators like ROC, MACD and RSI being high or low, or having been high or low for some period of time, is equally as effective as attempting doing so for the randomly generated movement.
// More rigorous tests of this conjecture could be done by expanding on the tools in this program.

public class Main {
    // String describing the program, to be printed when code is run
    public static String description = "This next portion of the program reads quotes data for a financial security from a .txt file and outputs a cleanly formatted version of the dates/times, closing prices, change, and several technical indicators, line-by-line.\nSPY and FAKE are the two symbols whose data are available in the \"Data Files\" folder within this repository. SPY is an S&P500 ETF; FAKE is a randomly generated motion over many hypothetical trading periods, created by this program's complementary python component.\nThe purpose is to illustrate the similarity in the movement of the stock market as a whole and a completely randomly generated motion, especially the technical indicators applied to them. Attempting to draw immediate conclusions about the next movement of the market based on the values of oscillators like ROC, MACD and RSI being high or low, or having been high or low for some period of time, is equally as effective as attempting doing so for the randomly generated movement.";
    
    public static void main(String[] args){
        // Declares a quotes class with name and interval of the security; reads the data from the file, if it exists, and stores it in ArrayLists within the object
        System.out.println(description);
        UserInput uin = new UserInput();
        Quotes SPY_1min = new Quotes(uin.symbol, uin.interval);
        // Creates several ArrayLists containing technical indicators, using methods from the quotes class, with numbers of trailing days declared as parameters
        ArrayList<Double> Change = SPY_1min.Change(1);
        ArrayList<Double> ROC = SPY_1min.ROC(12);
        ArrayList<Double> EMA = SPY_1min.EMA(14, 10);
        ArrayList<Double> MACD = SPY_1min.MACD(12, 26, 10);
        ArrayList<Double> RSI = SPY_1min.RSI(14);
        // Prints all ArrayLists line-by-line, from least to most recent
        // Prints in format: "(M)M/(D)D/YYYY (h)h:(m)m Close Change ROC EMA MACD RSI"
        // Cuts off 27 least recent data points to avoid attempting to output ArrayList values which to not exist due to cutoffs at ends of trailing calculations
        for (int c=SPY_1min.length-27; c>=0; c--){
            DecimalFormat f = new DecimalFormat("###.###"); // to prevent run-on doubles with dozens of digits after the decimal
            System.out.print(f.format(SPY_1min.month.get(c)) + "/" + f.format(SPY_1min.day.get(c)) + "/" + f.format(SPY_1min.year.get(c)) + "  ");
            System.out.print(f.format(SPY_1min.hour.get(c)) + ":" + SPY_1min.minute.get(c) + "  ");
            System.out.print(f.format(SPY_1min.close.get(c)) + "  " + f.format(Change.get(c)) + "  " + f.format(ROC.get(c)) + "%  ");
            System.out.println(f.format(EMA.get(c)) + "  " + f.format(MACD.get(c)) + "  " + f.format(RSI.get(c)));
        }
        System.out.println("Date        Time   Close  Change  ROC   EMA    MACD   RSI");
    }
}

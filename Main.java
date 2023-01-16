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
        Quotes in1 = new Quotes(uin.symbol, uin.interval);
        // Creates several ArrayLists containing technical indicators, using methods from the quotes class, with numbers of trailing days declared as parameters
        ArrayList<Double> Change = in1.Change(1);
        ArrayList<Double> ROC = in1.ROC(12);
        ArrayList<Double> EMA = in1.EMA(14, 10);
        ArrayList<Double> MACD = in1.MACD(12, 26, 10);
        ArrayList<Double> RSI = in1.RSI(14);
        // Prints all ArrayLists line-by-line, from least to most recent
        // Prints in format: "(M)M/(D)D/YYYY (h)h:(m)m Close Change ROC EMA MACD RSI"
        // Cuts off 27 least recent data points to avoid attempting to output ArrayList values which to not exist due to cutoffs at ends of trailing calculations
        for (int c=in1.length-27; c>=0; c--){
            DecimalFormat f = new DecimalFormat("###.###"); // to prevent run-on doubles with dozens of digits after the decimal
            System.out.print(f.format(in1.month.get(c)) + "/" + f.format(in1.day.get(c)) + "/" + f.format(in1.year.get(c)) + "  ");
            System.out.print(f.format(in1.hour.get(c)) + ":" + in1.minute.get(c) + "  ");
            System.out.print(f.format(in1.close.get(c)) + "  " + f.format(Change.get(c)) + "  " + f.format(ROC.get(c)) + "%  ");
            System.out.println(f.format(EMA.get(c)) + "  " + f.format(MACD.get(c)) + "  " + f.format(RSI.get(c)));
        }
        System.out.println("Date        Time   Close  Change  ROC   EMA    MACD   RSI");
        /// SPY_1min.print();
    }
}

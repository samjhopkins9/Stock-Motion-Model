import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Class represents all atributes of a period of data for a financial asset in ArrayList form
// Year, Month, Day, Hour, Minute, Open, High, Low, Close, Volume
// Contains several methods to calculate technical indicators
// Data from FirstRateData.com
public class Quotes {
    public int length = 0;
    public ArrayList<Integer> year = new ArrayList<>();
    public ArrayList<Integer> month = new ArrayList<>();
    public ArrayList<Integer> day = new ArrayList<>();
    public ArrayList<Integer> hour = new ArrayList<>();
    public ArrayList<Integer> minute = new ArrayList<>();
    public ArrayList<Double> open = new ArrayList<>();
    public ArrayList<Double> high = new ArrayList<>();
    public ArrayList<Double> low = new ArrayList<>();
    public ArrayList<Double> close = new ArrayList<>();
    public ArrayList<Integer> volume = new ArrayList<>();

    // Creates an instance of the quotes class using the symbol of the underlying security and the interval at which the data is being measured
    public Quotes(String name, String interval){
        // Creates a scanner to scan the contents of the data file, provided that it is in the Data Files folder
        Scanner fin = null;
        try {
            fin = new Scanner(new File("./Data Files/" + name + "/" + name + "_" + interval + ".txt"));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        // Scans content line by line
        // DataLine object reads line and returns an object with each attribute as a string
        // Each string is then converted into its correct type and appended to its ArrayList
        while(fin.hasNextLine()){
            DataLine strings = new DataLine(fin.nextLine());
            year.add(Integer.parseInt(strings.yr));
            month.add(Integer.parseInt(strings.mnth));
            day.add(Integer.parseInt(strings.dy));
            hour.add(Integer.parseInt(strings.hr));
            minute.add(Integer.parseInt(strings.mn));
            open.add(Double.parseDouble(strings.opn));
            high.add(Double.parseDouble(strings.hgh));
            low.add(Double.parseDouble(strings.lw));
            close.add(Double.parseDouble(strings.clse));
            volume.add(Integer.parseInt(strings.vlme));
            length++;
        }
    }

    // Prints the date, time, Open, High, Low, Close and Volume for each unit of data, line-by-line
    public void print(){
        for (int i=0; i<length; i++){
            System.out.print(month.get(i) + "/" + day.get(i) + "/" + year.get(i) + "  ");
            System.out.println(hour.get(i) + ":" + minute.get(i) + "  " + close.get(i) + " " + volume.get(i));
        }
    }
    
    // Calculates change between price x periods ago (usually 1) and current price for each line of data
    public ArrayList<Double> Change(int x){
        ArrayList<Double> change = new ArrayList<>();
        // the last x values are cut off from the calculation because at that point i+x will not exist, generating an error
        for (int i=0; i<this.length-x; i++){
            change.add(this.close.get(i+x) - this.close.get(i));
        }
        return change;
    }
    
    // Calculates rate of change between price x periods ago and current price for each line of data
    public ArrayList<Double> ROC(int x){
        ArrayList<Double> roc = new ArrayList<>();
        for (int i=0; i<this.length-x; i++){
            roc.add((this.close.get(i+x) - this.close.get(i))/this.close.get(i+x));
        }
        return roc;
    }

    // Calculates simple moving average over x periods for each line of data
    public ArrayList<Double> SMA(int x){
        ArrayList<Double> sma = new ArrayList<>();
        for (int i=0; i<this.length-x; i++){
            double sum = 0;
            for (int c=0; c<x; c++){
                sum += this.close.get(i+c);
            }
            sma.add(sum/x);
        }
        return sma;
    }

    // Calculates exponential moving average over x periods, with a weighting of w% given towards the price of the current day, for each line of data
    public ArrayList<Double> EMA(int x, int w){
        ArrayList<Double> ema = new ArrayList<>();
        for (int i=0; i<this.length-x; i++){
            double sum = 0;
            for (int c=0; c<x; c++){
                sum += this.close.get(i+c);
            }
            ema.add((this.close.get(i+x-1))*(w/100.0) + (sum/x)*((100.0-w)/100.0));
        }
        return ema;
    }

    // Calculates the difference between the exponential moving averages over a and b periods, with weightings of w% given to the price of the current day, for each line of data
    public ArrayList<Double> MACD(int a, int b, int w){
        ArrayList<Double> macd = new ArrayList<>();
        ArrayList<Double> ema1 = EMA(a, w);
        ArrayList<Double> ema2 = EMA(b, w);
        for (int i=0; i<this.length-b; i++){
            macd.add(ema1.get(i) - ema2.get(i));
        }
        return macd;
    }

    // Calculates the relative strength index over x periods for each line of data
    // The relative strength index calculation most importantly involves dividing the EMA of gains by the EMA of losses, with a weighting of 1/x given towards the most recent gain or loss, for x periods of data
    public ArrayList<Double> RSI(int x){
        ArrayList<Double> rsi = new ArrayList<>();
        for (int i=0; i<this.length-x-1; i++){
            double gain_sum = 0;
            double loss_sum = 0;
            double last_gain = 0;
            double last_loss = 0;
            // for loop gets sums for calculating average gain from unit i+c+1 to unit i+1
            // loops from least to most recent, updating last gain and last loss each time a gain or loss is reached
            for (int c=x; c>=1; c--){
                double change = this.close.get(i+c+1) - this.close.get(i+c);
                if (change > 0){
                    gain_sum += change;
                    last_gain = change;
                }
                else if (change <= 0){
                    loss_sum += Math.abs(change);
                    last_loss = Math.abs(change);
                }
            }
            // if statement updates last gain or loss to be current gain or loss
            if (this.close.get(i+1) - this.close.get(i) > 0){
                last_gain = this.close.get(i+1) - this.close.get(i);
            }
            else if (this.close.get(i+1) - this.close.get(i) <= 0){
                last_loss = Math.abs(this.close.get(i+1) - this.close.get(i));
            }
            double avg_gains = gain_sum/x;
            double avg_losses = loss_sum/x;
            double rs = ((x-1)*avg_gains + last_gain) / ((x-1)*avg_losses + last_loss);
            rsi.add(100-(100/(1+rs)));
        }
        return rsi;
    }
}

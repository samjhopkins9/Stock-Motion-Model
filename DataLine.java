// Class parses a single line of data and returns an object with each attribute contained in a separate string
public class DataLine {
    public String yr = "";
    public String mnth = "";
    public String dy = "";
    public String hr = "";
    public String mn = "";
    public String opn = "";
    public String hgh = "";
    public String lw = "";
    public String clse = "";
    public String vlme = "";
    
    // Loops through the string character by character, adds characters to certain strings based on index and number of commas passed, and skips irrelevant characters
    public DataLine(String line){
        int i = 0;
        int k = 0;
        while (i<line.length()){
            char c = line.charAt(i);
            if (c == '-' || c == ' ' || c == ':' || c == ',' || (i > 16 && i < 19)){
                if (c == ','){
                    k++;
                }
            }
            else if (i <= 3){
                yr += c;
            }
            else if (i <= 6){
                mnth += c;
            }
            else if (i <= 9){
                dy += c;
            }
            else if (i <= 12){
                hr += c;
            }
            else if (i <= 15){
                mn += c;
            }
            else if (i > 19){
                if (k == 1){
                    opn += c;
                }
                else if (k == 2){
                    hgh += c;
                }
                else if (k == 3){
                    lw += c;
                }
                else if (k == 4){
                    clse += c;
                }
                else if (k == 5){
                    vlme += c;
                }
            }
            i++;
        }
    }
}

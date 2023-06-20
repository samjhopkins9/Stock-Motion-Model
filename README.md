# Stock Price Motion Model

    ## Description
     - This program is meant to visually and numerically illustrate the randomness of the short-term movements in stock prices. It contains two components. 
     - The chart model portion, written in Python, attempts to model the intra-day, week, and month movements in stock prices based on the "Random Walk" theory, which hypothesizes that the movement of stocks throughout days, weeks and months is completely random -- subject to the random events of the world, that is, to which the professionals and the market as a whole respond so quickly.
     - The data crunching portion of this program, written in Java, reads quotes data for a financial security from a .txt file and outputs a cleanly formatted version of the dates/times, closing prices, change, and several technical indicators, line-by-line. SPY and FAKE are the two symbols whose data are available in the "Data Files" folder within this repository. SPY is an S&P500 ETF; FAKE is a randomly generated motion over many hypothetical trading periods, created by this program's complementary python component. The purpose is to illustrate the similarity in the movement of the stock market as a whole and a completely randomly generated motion, especially the technical indicators applied to them. Attempting to draw immediate conclusions about the next movement of the market based on the values of oscillators like ROC, MACD and RSI being high or low, or having been high or low for some period of time, is equally as effective as attempting to do so for the randomly generated movement.
    
    ## Predependencies
     - Python must be installed. If not, install here: https://www.python.org/downloads/macos/
     - Java must be installed. If not, install here: https://www.oracle.com/java/technologies/downloads/#jdk19-mac
     - PIP
        * Package management system for python
        * To install, navigate to project directory in bash terminal and type "python3 get-pip.py". The script is contained in the project and will install pip onto your machine
     - Matplotlib
        * Python module for graphing data
        * To install, type "pip install matplotlib" in your bash terminal
    
    ## How to run
     - Navigate to the project directory in your bash terminal and type **bash run.sh**
      * run.sh executes python and java components of the program consecutively
    
    ## Components
     _Comments explaining code are present in all listed components.
        #### run.sh
         * The bash script which compiles and executes the python and java files consecutively. This is what is run.
        ### chart_model.py
         * The main and only portion of the coin-flipping/charting component.
        ### Main.java
         * The main portion of the Java component, housing the execution of the most important code.
        ### Java files:
         - Quotes.java
         - UserInput.java
         - DataLine.java
          * Each .java file has a corresponding .class file contained within the folder, created upon the compilation of the program if not already present
        
    ## License
     * GNU General Public License v3.0, Sam Hopkins, Jan 10, 2023. Updated June 20, 2023.

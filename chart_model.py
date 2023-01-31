#!/usr/bin/python
import random
import matplotlib.pyplot as plt

### This short program attempts to model the intra-day, week and month movements in the stock market, according to the "Random Walk" Theory, which hypothesises that the movement of stocks throughout days, weeks and months is completely random -- subject to the random events of the world, that is, to which the professionals and the market as a whole respond so quickly.
### Hypothetical stock FAKE and its hypothetical prices over time are used as an example.

x_axis = []
prices = []

file = open("Data Files/FAKE_1min.txt", "w")
base = 3800.0

### Randomly generates 0 or 1 for every hypothetical minute of trading to simulate flipping a coin
### if 0, increase the stock by 0.01%; if 1, decrease by 0.01%

for i in range(400000):
    x_axis.append(i)
    prices.append(base)
    if random.randint(0, 1) == 0:
        base *= 1.0001
    else:
        base *= 0.9999

### Writes the closing price to a text file line-by-line, with all other information written present to format the data such that it can be read and executed by the Java portion of this program, with all numbers, punctuation, and duplicates present in the string for this purpose
for i in range(len(prices)):
    file.write("1111-11-11 11:11:11," + str(prices[i]) + "," + str(prices[i]) + "," + str(prices[i]) + "," + str(prices[i]) + ",1000\n")

### Describes purpose of program and graph when code is run
### print("This chart attempts to model the intra-day, week and month movements in the stock market, according to the \"Random Walk\" Theory, which hypothesises that the movement of stocks throughout days, weeks and months is completely random -- based on the random events of the world and subject to the rapid adjustments of professionals and the general market. Hypothetical stock FAKE and its hypothetical prices over time are used as an example.")
### print("Close the chart window when you are ready to continue.")
print()

### Plots and shows FAKE prices
plt.plot(x_axis, prices)
plt.show()


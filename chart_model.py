#!/usr/bin/python
import random
import matplotlib.pyplot as plt

### This short program models the intra-day, week and month movements in the stock market, according to the "Random Walk" Theory
### Hypothetical stock FAKE and its hypothetical prices over time are used as an example

x_axis = []
y_axes = [[], [], [], []]
files = [open("Data Files/FAKE/FAKE_1min.txt", "w"),
    open("Data Files/FAKE/FAKE_5min.txt", "w"),
    open("Data Files/FAKE/FAKE_30min.txt", "w"),
    open("Data Files/FAKE/FAKE_1hour.txt", "w"),
]

### generates 0 or 1 for every hypothetical minute of trading
### if 0, increase by 0.01%, if 1, decrease by 0.01%
### commented out portion subtracts .0001% from the decrease to attempt to model long-term increases
for c in range(4):
    base = 3810.20 ### price of SPX as of EOD 12/5/23
    for i in range(400000):
        if (c%4 == 0):
            x_axis.append(i)
        y_axes[c].append(base)
        if random.randint(0, 1) == 0:
            base *= 1.0001
        else:
            base *= 0.9999

for c in range(4):
    for i in range(len(y_axes[c])):
       files[c].write("1001-11-11 11:11:11," + str(y_axes[c][i]) + "," + str(y_axes[c][i]) + "," + str(y_axes[c][i]) + "," + str(y_axes[c][i]) + ",1000\n")

plt.plot(x_axis, y_axes[0])
plt.show()


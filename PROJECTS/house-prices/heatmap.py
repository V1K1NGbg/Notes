# import pandas as pd
# import matplotlib.pyplot as plt
# # from pyproj import Proj


# training = pd.read_csv("PROJECTS/house-prices/HousePriceDataTRAINING.csv",names=["x", "y", "time", "price", "beds"])

# #training check devision

# print(training.x)
# print(training.y)

# print(training.x.get(0))
# # print(training.size)

# storage = pd.new

# for i in range(training.size) :
#     d[training.x.get(i)] = training.y.get(i)

# print(type(d.values()))
# print(type(training.y))


# training.plot.scatter(x="d.values()",y="d.keys()")
# training.plot.scatter(x="y",y="x")

# plt.show()

import csv
import random
import matplotlib.pyplot as plt

import pyproj

p29 = pyproj.Proj(proj='utm', zone=29, ellps='WGS84')
p30 = pyproj.Proj(proj='utm', zone=30, ellps='WGS84')

x = []
y = []

xcalc = []
ycalc = []

with open('PROJECTS/house-prices/HousePriceDataTRAINING.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        # if random.randint(1, 100) <= 1:
        #   if line_count > 10000:
        #       break
            x.append(float(row[1]))
            y.append(float(row[0]))
            line_count  = line_count + 1

    print(f'Processed {line_count} lines.')


# for i in range(len(x)):
#     if x[i] >= -6:
#         a, b = p30(x, y)
#     else:
#         a, b = p29(x, y)
#     xcalc.append(a)
#     ycalc.append(b)

#cashe


plt.scatter(x, y)
#plt.scatter(xcalc, ycalc)

plt.show()
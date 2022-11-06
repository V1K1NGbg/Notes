import pandas as pd
import matplotlib.pyplot as plt
from pyproj import Proj

training = pd.read_csv("PROJECTS/house-prices/HousePriceDataTRAINING.csv",names=["x", "y", "time", "price", "beds"])

print(training.x)
print(training.y)

print(training.x.get(0))
print(training.size)

d = {}

for i in range(training.size):
    d[training.x.get(i)] = training.y.get(i)

print(d.items())


# training.plot.scatter(x="d.values()",y="d.keys()")
# training.plot.scatter(x="y",y="x")

plt.show()
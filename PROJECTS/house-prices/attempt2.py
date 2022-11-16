import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import mpl_toolkits
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn import ensemble
import math

def remove_outliers(oneD_array, deviations):
    # w =
    variance = ((oneD_array - np.average(oneD_array)) ** 2 ).sum()/len(oneD_array)
    deviation = math.sqrt(variance)
    outlier_bool = np.logical_and(oneD_array < (np.average(oneD_array) + deviations * deviation), oneD_array > (np.average(oneD_array) - deviations * deviation))
    outliers = oneD_array[outlier_bool]

def data_accuracy(predictions, real):
    p, r = predictions, real
    w = 1.5
    array = np.stack((p, r), axis=1)
    difference = np.abs(array[:,0]-array[:,1])
    percentages = (difference/np.abs(array[:,1]))*100
    median = percentages[int(len(percentages)/2)]
    # removes top and bottom 5% respectively
    without_outliers = remove_outliers(percentages, 2)

    print(f"average of 90%: {np.average(without_outliers)}")
    print(f"Median is: {percentages[median]}")

data = pd.read_csv("PROJECTS/house-prices/HousePriceDataTRAINING.csv")
data.columns = ["long","lat","date","price","bed"]

reg = LinearRegression()


labels = data['price']

conv_dates = [0 if ("2011" in values or "2012" in values or "2013" in values or "2014" in values or "2015" in values or "2016" in values) else 1 for values in data.date ]

data['date'] = conv_dates

train1 = data.drop('price',axis=1)

x_train , x_test , y_train , y_test = train_test_split(train1 , labels , test_size = 0.10, random_state =2)

reg.fit(x_train,y_train)
x_pred = reg.predict(x_test)

print(data_accuracy(y_test, x_pred))
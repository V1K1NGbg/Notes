import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import mpl_toolkits
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn import ensemble
import math

def data_accuracy(predictions, real):
    # This will be a list, the ith element of this list will be abs(prediction[i] - real[i])/real[i]
    differences = list(map(lambda x: abs(x[0] - x[1]) / x[1], zip(predictions, real)))

    # Find the value for the bottom t percentile and the top t percentile
    f = 5
    t = 95
    percentiles = np.percentile(differences, [f, t])
    differences_filter = []
    for diff in differences:
        # Keep only values in between f and t percentile
        if percentiles[0] < diff < percentiles[1]:
            differences_filter.append(diff)

    # In this example, we are removing everything above the 95th percentile as t is 95
    # and everything below the 5th percentile as f is 5

    # The higher the value, the worse the guess, therefore removing everything above the 95th percentile will remove 
    # the 5% worse guesses 

    
    print(f"Differences excluding outliers: {np.average(differences_filter)}")
    print(f"Differences: {np.average(differences)}")

def data_accuracy2(predictions, real): # as lists
    p, r = predictions, real
    w = 1.5 # Deviations
    array = np.stack((p, r), axis=1)
    difference = np.abs(array[:,0]-array[:,1])
    percentages = (difference/np.abs(array[:,1]))*100
    sorted_per = percentages[percentages[:].argsort()]
    median = sorted_per[int(len(percentages)/2)]

    # removes top and bottom 5% respectively
    variance = ((percentages - np.average(percentages)) ** 2 ).sum()/len(percentages)
    deviation = math.sqrt(variance)
    outlier_bool = np.logical_and(percentages < (np.average(percentages) + w * deviation), percentages > (np.average(percentages) - w * deviation))
    outliers = percentages[outlier_bool]

    print(f"average of 90%: {np.average(outliers)}")
    print(f"Median is: {median}")

clf = ensemble.GradientBoostingRegressor(n_estimators = 50, max_depth = 8, min_samples_split = 3,
          learning_rate = 0.0001, loss = 'squared_error')

data = pd.read_csv("PROJECTS/house-prices/HousePriceDataTRAINING.csv")
data.columns = ["long","lat","date","price","bed"]

reg = LinearRegression()


labels = data['price']

conv_dates = [0 if ("2011" in values or "2012" in values or "2013" in values or "2014" in values or "2015" in values or "2016" in values) else 1 for values in data.date ]

data['date'] = conv_dates

train1 = data.drop('price',axis=1)

x_train , x_test , y_train , y_test = train_test_split(train1 , labels , test_size = 0.10, random_state =2)

clf.fit(x_train, y_train)

print(x_train, x_test)

x_pred = clf.predict(x_test)

print(clf.score(x_test,y_test))

print(data_accuracy(y_test, x_pred))
print(data_accuracy2(y_test, x_pred))
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import mpl_toolkits
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn import ensemble

clf = ensemble.GradientBoostingRegressor(n_estimators = 500, max_depth = 5, min_samples_split = 2,
          learning_rate = 0.1, loss = 'squared_error')

data = pd.read_csv("PROJECTS/house-prices/HousePriceDataTRAINING2.csv")

reg = LinearRegression()


labels = data['price']

conv_dates = [0 if ("2011" in values or "2012" in values or "2013" in values or "2014" in values or "2015" in values or "2016" in values) else 1 for values in data.date ]

data['date'] = conv_dates

train1 = data.drop('price',axis=1)

x_train , x_test , y_train , y_test = train_test_split(train1 , labels , test_size = 0.10, random_state =2)

clf.fit(x_train, y_train)

print(clf.score(x_test,y_test))
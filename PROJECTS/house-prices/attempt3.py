from sklearn import ensemble
import pandas as pd

clf = ensemble.GradientBoostingRegressor(n_estimators = 400, max_depth = 5, min_samples_split = 2,
          learning_rate = 0.1, loss = 'ls')

data = pd.read_csv("PROJECTS/house-prices/HousePriceDataTRAINING2.csv")

labels = data['price']

conv_dates = [0 if ("2011" in values or "2012" in values or "2013" in values or "2014" in values or "2015" in values or "2016" in values) else 1 for values in data.date ]

data['date'] = conv_dates

train1 = data.drop('price',axis=1)

x_train , x_test , y_train , y_test = train_test_split(train1 , labels , test_size = 0.10, random_state =2)

clf.fit(x_train, y_train)
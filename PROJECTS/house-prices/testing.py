import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn import ensemble


def data_accuracy(predictions, real):
    """
    Check the accuracy of the estimated prices
    """
    # This will be a list, the ith element of this list will be abs(prediction[i] - real[i])/real[i]
    differences = list(map(lambda x: abs(x[0] - x[1]) / x[1], zip(predictions, real)))

    # Find the value for the bottom t percentile and the top t percentile
    f = 0
    t = 90
    percentiles = np.percentile(differences, [f, t])
    differences_filter = []
    for diff in differences:
        # Keep only values in between f and t percentile
        if percentiles[0] < diff < percentiles[1]:
            differences_filter.append(diff)

    print(f"Differences excluding outliers: {np.average(differences_filter)}")


# clf = ensemble.GradientBoostingRegressor(n_estimators = 1100, max_depth = 15, min_samples_split = 9,learning_rate = 0.5, loss = 'squared_error')
# clf = ensemble.GradientBoostingRegressor(n_estimators = 1000, max_depth = 15, min_samples_split = 9, learning_rate = 0.2, loss = 'squared_error')
clf = ensemble.GradientBoostingRegressor(n_estimators = 600, max_depth = 7, min_samples_split = 5, learning_rate = 0.7, loss = 'squared_error')


data = pd.read_csv("PROJECTS/house-prices/HousePriceDataTRAINING.csv")
data.columns = ["long", "lat", "date", "price", "bed"]


# conv_dates = [0 if ("2011" in values or "2012" in values or "2013" in values or "2014" in values or "2015" in values or "2016" in values) else 1 for values in data.date ]

conv_dates = []

for i in range(data.date.size):
    conv_dates.append(abs(int(data.at[i, "date"].split("/")[0]) + int(data.at[i, "date"].split("/")[1])*31 + int(data.at[i, "date"].split("/")[2])*366 - 737691))

data['date'] = conv_dates

                                                                                                                                                                                                                                                                                                                                                                    # i have a small penis
labels = data['price']
train1 = data.drop('price', axis=1)

x_train, x_test, y_train, y_test = train_test_split(
    train1, labels, test_size=0.10)

# y_train = list(map(lambda p: np.log2(p), y_train))

clf.fit(x_train, y_train)

# x_pred = list(map(lambda p: 2**p, clf.predict(x_test)))
x_pred = clf.predict(x_test)

# print(clf.get_params())

print(data_accuracy(y_test, x_pred))
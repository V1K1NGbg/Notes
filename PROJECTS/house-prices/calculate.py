import csv
import numpy as np

with open('PROJECTS/house-prices/cashe10.csv') as csv_file:
    csvreader = csv.reader(csv_file, delimiter=',')

    x = np.array(next(csvreader)).astype('int32')
    y = np.array(next(csvreader)).astype('int32')
    bed = np.array(next(csvreader)).astype('int32')
    date = np.array(next(csvreader)).astype('int32')
    price = np.array(next(csvreader)).astype('float64')
    info = np.array(next(csvreader)).astype('float64')
    testx = np.array(next(csvreader)).astype('int32')
    testy = np.array(next(csvreader)).astype('int32')
    testbed = np.array(next(csvreader)).astype('int32')
    testdate = np.array(next(csvreader)).astype('int32')
    testprice = np.array(next(csvreader)).astype('float64')
    testinfo = np.array(next(csvreader)).astype('float64')

def get_closest(array, values):
    idxs = np.searchsorted(array, values, side="left")
    prev_idx_is_less = ((idxs == len(array))|(np.fabs(values - array[np.maximum(idxs-1, 0)]) < np.fabs(values - array[np.minimum(idxs, len(array)-1)])))
    idxs[prev_idx_is_less] -= 1
    
    return array[idxs]

xy = []

for i in range(len(x)):
    idk = []
    idk.append(x[i])
    idk.append(y[i])
    xy.append(idk)

print(xy)
# print(get_closest(xy, [20000000, 20000000])) ???
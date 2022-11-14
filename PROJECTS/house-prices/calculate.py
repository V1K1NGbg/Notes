import csv
import numpy as np

x = []
y = []
bed = []
date = []
price = []

testx = []
testy = []
testbed = []
testdate = []
testprice = []

with open('PROJECTS/house-prices/cashe10.csv') as csv_file:
    csvreader = csv.reader(csv_file, delimiter=',')

    x = np.array([next(csvreader), next(csvreader)]).astype('int32')
    bed = np.array(next(csvreader)).astype('int32')
    date = np.array(next(csvreader)).astype('int32')
    price = np.array(next(csvreader)).astype('float64')
    info = np.array(next(csvreader)).astype('float64')
    testx = np.array([next(csvreader), next(csvreader)]).astype('int32')
    testbed = np.array(next(csvreader)).astype('int32')
    testdate = np.array(next(csvreader)).astype('int32')
    testprice = np.array(next(csvreader)).astype('float64')
    testinfo = np.array(next(csvreader)).astype('float64')

def find_nearest_vector(array, value):
  idx = np.array([np.linalg.norm(x+y) for (x,y) in array-value]).argmin()
  return array[idx]

print(x)

print(find_nearest_vector(x, [20000000,20000000]))
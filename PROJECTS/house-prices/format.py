import csv
import random
import sys


def formatting_data(arr):
    minarr = sys.maxsize
    maxarr = -sys.maxsize - 1
    for i in range(len(arr)):
        arr[i] = int(arr[i] * 10000000)
        if minarr > arr[i]:
            minarr = arr[i]
        if maxarr < arr[i]:
            maxarr = arr[i]

    for i in range(len(arr)):
        arr[i] = arr[i] - minarr
    maxarr = maxarr - minarr

    return arr, maxarr


def formatting_time(arr):
    mintime = sys.maxsize
    maxtime = -sys.maxsize - 1
    for i in range(len(arr)):
        arr[i] = int(arr[i].split("/")[0]) + int(arr[i].split("/")
                                                 [1])*31 + int(arr[i].split("/")[2])*365
        if mintime > arr[i]:
            mintime = arr[i]
        if maxtime < arr[i]:
            maxtime = arr[i]

    for i in range(len(arr)):
        arr[i] = arr[i] - mintime
    maxtime = maxtime - mintime

    return arr, maxtime


def formatting_price(arr):
    minprice = sys.maxsize
    maxprice = -sys.maxsize - 1
    avg = 0
    for i in range(len(arr)):
        avg = avg + arr[i]
        if minprice > arr[i]:
            minprice = arr[i]
        if maxprice < arr[i]:
            maxprice = arr[i]

    avg = avg / len(arr)

    for i in range(len(arr)):
        arr[i] = arr[i] / avg
    maxprice = maxprice / avg

    return arr, maxprice, avg

def formatd(arr1, arr2, arr3, arr4, arr5):
    arr1calc, maxarr1 = formatting_data(arr1)
    arr2calc, maxarr2 = formatting_data(arr2)

    arr4calc, maxarr4 = formatting_time(arr4)

    arr5calc, maxarr5, avgarr5 = formatting_price(arr5)

    maxarr3 = arr3[0]

    for i in range(len(arr3)):
        if arr3[i] > maxarr3:
            maxarr3 = arr3[i]

    return arr1calc, arr2calc, arr4calc, arr5calc, maxarr1, maxarr2, maxarr3, maxarr4, maxarr5, avgarr5

# p29 = pyproj.Proj(proj='utm', zone=29, ellps='WGS84')
# p30 = pyproj.Proj(proj='utm', zone=30, ellps='WGS84')

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


with open('PROJECTS/house-prices/HousePriceDataTRAINING.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')

    data = 0

    for row in csv_reader:
        if random.randint(1, 100) <= 80:

            # if data >= 20:
            #     break
            # if data % 2 == 0:
                y.append(float(row[0]))
                x.append(float(row[1]))
                date.append(row[2])
                price.append(int(row[3]))
                bed.append(int(row[4]))

        else:

            # if data % 2 != 0:
                testy.append(float(row[0]))
                testx.append(float(row[1]))
                testdate.append(row[2])
                testprice.append(int(row[3]))
                testbed.append(int(row[4]))
            
        data = data + 1

    print(f'Processed {data} lines.')

# projection if time left

xcalc, ycalc, datecalc, pricecalc, maxx, maxy, maxb, maxd, maxp, avgprice = formatd(x, y, bed, date, price)

testxcalc, testycalc, testdatecalc, testpricecalc, testmaxx, testmaxy, testmaxb, testmaxd, testmaxp, testavgprice = formatd(testx, testy, testbed, testdate, testprice)

with open("PROJECTS/house-prices/cashe80p.csv", 'w') as csvfile:
    csvwriter = csv.writer(csvfile)

    csvwriter.writerow(xcalc)
    csvwriter.writerow(ycalc)
    csvwriter.writerow(bed)
    csvwriter.writerow(datecalc)
    csvwriter.writerow(pricecalc)

with open("PROJECTS/house-prices/cashe80p (test).csv", 'w') as csvfile:
    csvwriter = csv.writer(csvfile)

    csvwriter.writerow(testxcalc)
    csvwriter.writerow(testycalc)
    csvwriter.writerow(testbed)
    csvwriter.writerow(testdatecalc)
    csvwriter.writerow(testpricecalc)

import numpy as np


def remove_outliers(oneD_array, deviations):
    # w =
    variance = ((oneD_array - np.average(oneD_array)) ** 2 ).sum()/len(oneD_array)
    deviation = math.sqrt(variance)
    outlier_bool = np.logical_and(oneD_array < (np.average(oneD_array) + deviations * deviation), oneD_array > (np.average(oneD_array) - deviations * deviation))
    outliers = oneD_array[outlier_bool]

# Calculates median & average error percentage
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


print(data_accuracy())
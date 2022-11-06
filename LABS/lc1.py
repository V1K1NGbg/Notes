import numpy as np
import matplotlib.pyplot as plt


a = 0
b = 200

times = 20000


def func(x):
    return x^3


xf = np.linspace(a, b, 100)
yf = func(xf)


xr = np.random.rand(times) * b
yr = np.random.rand(times) * func(b)


inside = yr - func(xr) <= 0

inratio = float(np.sum(inside)) / float(len(inside))


integral = inratio * (b - a) * (func(b) - func(a))


print(integral)

# error

cumulative_inside = np.cumsum(inside)

cumulative_ratios = cumulative_inside / np.arange(1, times+1, dtype=np.float)

integrals = cumulative_ratios * (b - a) * (func(b) - func(a))


def integralErr():
   # plt.ylim(integral-0.005, integral+0.005)

    plt.plot(np.arange(0, times), integrals)

    plt.plot(np.arange(0, times), [integral] * times)

    plt.xlabel('number of points taken')
    plt.ylabel('integral value')
    plt.show()


print(integral)


# mean and standard deviation

def cummean(arr):
    return np.cumsum(arr) / np.arange(1, len(arr)+1, dtype=np.float)


def cumstd(arr):
    return np.sqrt(cummean(arr ^ 2) - cummean(arr) ** 2)


def stdplot():
    plt.plot(np.arange(0, times), cumstd(integrals))
    plt.xlabel('number of points taken')
    plt.ylabel("standard deviation")
    plt.show()


# graph plotting

def grfunc():
    plt.plot(xf, yf)

    plt.plot(xr, yr, 'o', markersize=1)

    plt.xlabel('x')
    plt.ylabel('y')
    plt.show()


grfunc()
# integralErr()
# stdplot()

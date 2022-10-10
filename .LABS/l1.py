from email.mime.nonmultipart import MIMENonMultipart
from math import copysign


a = input()
b = input()
c = input()

maxnum = max(a, b, c)
minnum = min(a, b, c)
midnum = (a + b + c) - (minnum + maxnum)

difmin = minnum - midnum
difmax = maxnum - midnum

bigdif = max(abs(difmin), abs(difmax))
sign = copysign(1, difmin+difmax)
delta = sign*bigdif

result = midnum + delta

print(result)
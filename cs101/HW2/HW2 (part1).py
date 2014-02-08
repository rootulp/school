#Rootul Patel 9/10/13
#HW2 - Loops
#Part 1

import math
print ("The approximate the value of e is...")
e = float(0)
for i in range (0,16):
    e = e + (.5*(i+1.0)/(math.factorial(i)))
    print e

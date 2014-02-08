#Rootul Patel 9/10/13
#HW2 - Loops
#Part 2

import math
print ("The approximate the value of pi is...")
pi = float(0)
for i in range (1,500):
    pi = pi + (4.0*(((-1.0)**(i+1.0))/((2.0*i)-1)))
    print pi

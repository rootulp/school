#Rootul Patel 9/9/13
#HW4 Part 1 - Quadratic Equation

import math
print ("Quadratic Equations Solver!")
a = float(raw_input("Enter A: "))
b = float(raw_input("Enter B: "))
c = float(raw_input("Enter C: "))
if a == 0 and b == 0:
    print ("There are no real roots")
elif (b**2)-(4*a*c) < 0:
    print ("There are no real roots")
elif a == 0 and b != 0:
    solution1 = (-c)/b
    print ("There is one real solution:"),(solution1)
elif (b**2)-(4*a*c) == 0:
    solution1 = (-b)/(2*a)
    print ("There is one real solution:"),(solution1)
elif (b**2)-(4*a*c) > 0:
    solution1 = ((-b) + (math.sqrt((b**2)-(4*a*c))))/(2*a)  
    solution2 = ((-b) - (math.sqrt((b**2)-(4*a*c))))/(2*a)
    print ("There are two real solutions:"),(solution1),("and"),(solution2)
else:
    print ("Error")

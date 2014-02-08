#Rootul Patel 9/9/13
#HW1 - Quadratic Equation

import math
print ("Quadratic Equations Solver!")
a = float(raw_input("Enter A: "))
b = float(raw_input("Enter B: "))
c = float(raw_input("Enter C: "))
solution1 = ((-b) + (math.sqrt((b**2)-(4*a*c))))/(2*a)
solution2 = ((-b) - (math.sqrt((b**2)-(4*a*c))))/(2*a)
print ("The solutions are:"),(solution1),("and"),(solution2)
          


#Rootul Patel 9/18/13
#HW4 Part 2
#Speeding Tickets

speedLimit = int(raw_input("What was the speed limit? "))
speedDriver = int(raw_input("What was the driver's speed? "))
totalFine = 0
speedDiference = 0

if speedDriver > speedLimit:
    speedDiference = (speedDriver - speedLimit)
    totalFine = (speedDiference*5) + 50
    if speedDriver >= 90:
        totalFine = totalFine + 100
    
print ("Total Fine: $"), totalFine


    

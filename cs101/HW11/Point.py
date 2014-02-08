# Ames Fall 2013

import math

# Point class, contains two attributes: x and y
class Point:
    
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

    def __str__(self):
        return "(%4.2f, %4.2f)" % (self.x, self.y)

    def movePoint(self, dx=0, dy=0):
        self.x += dx
        self.y += dy

    # return polar coordinates: R,Theta as a tuple  (theta in degrees)
    def getPolar(self):
        r = math.sqrt(self.x**2 + self.y**2)
        theta = math.atan2(self.y, self.x) *180./math.pi
        return r,theta

    def plot(self, canvas):
        canvas.create_line(self.x, self.y, self.x+1, self.y+1)

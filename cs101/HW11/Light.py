#Rootul Patel 11-20-13
#HW11 ChristmasTree

from Tkinter import *
from Point import *
from Ornament import *
import random

class Light(Ornament):
    def __init__(self, canvas, width, height):
        super(Light, self).__init__(canvas, width, height)

    def addLight(self):
        colorNumber = random.randrange(0,2)
        if colorNumber == 0:
            color = "Red"
        else:
            color = "Green"
        Location = super(Light,self).choosePointInTriangle()
        self.canvas.create_oval(Location.x, Location.y, Location.x + 6, Location.y + 6, fill=color)
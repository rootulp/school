#Rootul Patel 11-20-13
#HW11 ChristmasTree

from Tkinter import *
from Point import *
from Light import *
import random

blinkingLights = []

class BlinkingLight(Light):
    def __init__(self, canvas, width, height):
        super(BlinkingLight, self).__init__(canvas, width, height)
        self.blinkingLights = []

    def addBlinkingLight(self):
        colorNumber = random.randrange(0,2)
        if colorNumber == 0:
            color = "Red"
        else:
            color = "Green"
        Location = super(Light,self).choosePointInTriangle()
        dot = self.canvas.create_oval(Location.x, Location.y, Location.x + 6, Location.y + 6, fill=color)
        self.blinkingLights.append(dot)

    def blink(self):
        for image in self.blinkingLights:
            if random.randrange(0,8)==0:
                self.canvas.itemconfigure(image, state=HIDDEN)
            else:
                self.canvas.itemconfigure(image, state=NORMAL)
        self.canvas.after(500, self.blink)
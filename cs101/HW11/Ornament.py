#Rootul Patel 11-20-13
#HW11 ChristmasTree

from Tkinter import *
from Point import *
import random

images = []
class Ornament(object):

    def __init__(self, canvas, width, height):
        self.canvas = canvas
        self.width = width
        self.height = height

    def addBackground(self, filename):
        Background = PhotoImage(file=filename)
        self.canvas.create_image(self.width/2, self.height/2, image=Background)
        images.append(Background)

    def choosePointInTriangle(self, pA=Point(87,20), pB=Point(12,270), pC=Point(162,270)): 
        a = random.random()
        b = random.random()
        if a+b > 1:
            a = 1-a
            b = 1-b
        c = 1-a-b
        x = int(a*pA.x + b*pB.x + c*pC.x)
        y = int(a*pA.y + b*pB.y + c*pC.y)
        return Point(x,y)

    def addOrnament(self, filename):
        Location = self.choosePointInTriangle()
        Ornament = PhotoImage(file=filename)
        self.canvas.create_image(Location.x, Location.y, image=Ornament)
        images.append(Ornament)



    # self.canvas.after(250, self.blink)
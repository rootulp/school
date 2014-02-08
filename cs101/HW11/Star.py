#Rootul Patel 11-20-13
#HW11 ChristmasTree

from Tkinter import *
from Point import *
from Ornament import *
import random

class Star(Ornament):
    def __init__(self, canvas, width, height):
        super(Star, self).__init__(canvas, width, height)
    def addStar(self, filename):
        Star = PhotoImage(file=filename)
        self.canvas.create_image(87, 20, image=Star)
        images.append(Star)
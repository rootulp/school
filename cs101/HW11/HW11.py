#Rootul Patel 11-20-13
#HW11 ChristmasTree

from Tkinter import *
from Point import *
from Ornament import *
from Star import *
from Light import *
from BlinkingLight import *
import random

window = Tk()
window.title("Merry Christmas")
Background = PhotoImage(file="tree.gif")
width = Background.width()
height = Background.height()
canvas = Canvas(window, width = width, height = height)
canvas.grid(row=0, column=0)
canvas.create_image(width/2, height/2, image=Background)

Ornament = Ornament(canvas, width, height)
Star = Star(canvas, width, height)
Light = Light(canvas, width, height)
BlinkingLight = BlinkingLight(canvas, width, height)

Star.addStar("star.gif")
for i in range(7):
    Ornament.addOrnament("smallOrnament.gif")
    Light.addLight()
    BlinkingLight.addBlinkingLight()
    BlinkingLight.blink()

window.mainloop()
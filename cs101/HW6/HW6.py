#Rootul Patel 9/29/13
#HW6 Fish Tank

import math
import time
import random
from Tkinter import *

window = Tk()
window.title("Fish Tank")

tank = PhotoImage(file="tank.gif")
fishLeft = PhotoImage(file="fishLeft.gif")
fishRight = PhotoImage(file="fishRight.gif")

windowWidth = tank.width()
windowHeight = tank.height()

canvas = Canvas(window, width = windowWidth, height = windowHeight)
canvas.grid(row = 0, column = 0)
canvas.create_image(0, 0, image = tank, anchor=NW )

def newFish():
    x = random.randrange(0, windowWidth)
    y = random.randrange(0, windowHeight)
    dx = random.randrange(-3, 4)
    dy = random.randrange(-3, 4)
    left = canvas.create_image(-1000, -1000, image = fishLeft)
    right = canvas.create_image(-1000, -1000, image = fishRight)
    updateFish(x, y, dx, dy, left, right)
               
def updateFish(x , y, dx, dy, left, right):
    if x < 0:
        dx = -dx
    elif x > windowWidth:
        dx = -dx
    if y < 0:
        dy = -dy
    elif y > windowHeight:
        dy = -dy
    if dx >= 0:
        current = right
        noncurrent = left
    else:
        current = left
        noncurrent = right
    x = x + dx
    y = y + dy
    canvas.coords(current, x, y)
    canvas.coords(noncurrent, -1000, -1000)
    canvas.after(10, updateFish, x, y, dx, dy, left, right)

addFishButton = Button(window, text = "Add New Fish", command = newFish)
addFishButton.grid(row = 1, column = 0)

window.mainloop()

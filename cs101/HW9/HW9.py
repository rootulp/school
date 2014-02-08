#Rootul Patel 10/28/13
#HW9 - FlowSolver

from Tkinter import *

#Define window / lists / constants
window = Tk()
window.title("Flow Solver")
gridSize = 6
clickCount = 0
tileCount = 0
endPoints = []
tileList = []
cells = []
colorList = ["blue", "green", "red", "yellow", "cyan", "orange", "magenta", "pink", "white"]
endPointFileList = ["blueEndpoint.gif", "greenEndpoint.gif", "redEndpoint.gif",
                    "yellowEndpoint.gif", "cyanEndpoint.gif", "orangeEndpoint.gif",
                    "magentaEndpoint.gif", "pinkEndpoint.gif", "whiteEndpoint.gif"]
fileList = ["blueHorizontal.gif",    "blueQ1.gif",    "blueQ2.gif",    "blueQ3.gif",    "blueQ4.gif",    "blueVertical.gif",
            "greenHorizontal.gif",   "greenQ1.gif",   "greenQ2.gif",   "greenQ3.gif",   "greenQ4.gif",   "greenVertical.gif",
            "redHorizontal.gif",     "redQ1.gif",     "redQ2.gif",     "redQ3.gif",     "redQ4.gif",     "redVertical.gif",
            "yellowHorizontal.gif",  "yellowQ1.gif",  "yellowQ2.gif",  "yellowQ3.gif",  "yellowQ4.gif",  "yellowVertical.gif",
            "cyanHorizontal.gif",    "cyanQ1.gif",    "cyanQ2.gif",    "cyanQ3.gif",    "cyanQ4.gif",    "cyanVertical.gif",
            "orangeHorizontal.gif",  "orangeQ1.gif",  "orangeQ2.gif",  "orangeQ3.gif",  "orangeQ4.gif",  "orangeVertical.gif",
            "magentaHorizontal.gif", "magentaQ1.gif", "magentaQ2.gif", "magentaQ3.gif", "magentaQ4.gif", "magentaVertical.gif",
            "pinkHorizontal.gif",    "pinkQ1.gif",    "pinkQ2.gif",    "pinkQ3.gif",    "pinkQ4.gif",    "pinkVertical.gif",
            "whiteHorizontal.gif",   "whiteQ1.gif",   "whiteQ2.gif",   "whiteQ3.gif",   "whiteQ4.gif",   "whiteVertical.gif"]

for filename in endPointFileList:
    photo = PhotoImage(file="Cell images/" + filename)
    for color in colorList:
        if color in filename:
            photo.color = color
    photo.goesUp = photo.goesDown = photo.goesLeft = photo.goesRight = True
    endPoints.append(photo)
for filename in fileList:
    photo = PhotoImage(file="Cell images/" + filename)
    if "Vertical" in filename or "Q1" in filename or "Q2" in filename:
        photo.goesUp = True
    else:
        photo.goesUp = False
    if "Vertical" in filename or "Q3" in filename or "Q4" in filename:
        photo.goesDown = True
    else:
        photo.goesDown = False
    if "Horizontal" in filename or "Q1" in filename or "Q4" in filename:
        photo.goesRight = True
    else:
        photo.goesRight = False
    if "Horizontal" in filename or "Q2" in filename or "Q3" in filename:
        photo.goesLeft = True
    else:
        photo.goesLeft = False
    for color in colorList:
        if color in filename:
            photo.color = color
    tileList.append(photo)

empty = PhotoImage(file="Cell images/Empty.gif")
empty.goesUp = empty.goesDown = empty.goesLeft = empty.goesRight = True

wall = PhotoImage(file="Cell images/Wall.gif")
wall.goesUp = wall.goesDown = wall.goesLeft = wall.goesRight = False

#Build Buttons
def makeCells():
    global cells
    for row in range (0,gridSize+2):
        cells.append([])
        for col in range (0,gridSize+2):
            if row == 0 or col == 0 or row == gridSize+1 or col == gridSize+1:
                button = Button(window, image = wall)
                button.image = wall
                cells[row].append(button)
            else:
                button = Button(window, image = empty)
                button.image = empty
                button.grid(row = row, column = col)
                cells[row].append(button)
                def press(b=button):
                    global clickCount
                    if clickCount < 18:
                        b.config(image = endPoints[clickCount/2])
                        b.image = endPoints[clickCount/2]
                        clickCount = clickCount + 1
                    else:
                        b.config(image = empty)
                        b.image = empty
                button.config(command = press)
makeCells()

#Solver

def isAllowedRight(curr, right):
    if right==empty:
        return True
    if curr.goesRight and right.goesLeft and curr.color==right.color:
        return True
    if not curr.goesRight and not right.goesLeft:
        return True
    if not curr.goesRight and right in endPoints:
        return True
    return False

def isAllowedLeft(curr, left):
    if left==empty:
        return True
    if curr.goesLeft and left.goesRight and curr.color==left.color:
        return True
    if not curr.goesLeft and not left.goesRight:
        return True
    if not curr.goesLeft and left in endPoints:
        return True
    return False

def isAllowedAbove(curr, up):
    if up==empty:
        return True
    if curr.goesUp and up.goesDown and curr.color==up.color:
        return True
    if not curr.goesUp and not up.goesDown:
        return True
    if not curr.goesUp and up in endPoints:
        return True
    return False

def isAllowedBelow(curr, down):
    if down==empty:
        return True
    if curr.goesDown and down.goesUp and curr.color==down.color:
        return True
    if not curr.goesDown and not down.goesUp:
        return True
    if not curr.goesDown and down in endPoints:
        return True
    return False

def checkCell(row, col):
    global cells
    curr = cells[row][col].image
    right = cells[row][col+1].image
    left = cells[row][col-1].image
    down = cells[row+1][col].image
    up = cells[row-1][col].image
    if isAllowedRight(curr, right) and isAllowedLeft(curr, left) and isAllowedAbove(curr, up) and isAllowedBelow(curr, down):
        return True
    return False

def checkEndpoint():
    global cells
    for row in range (1,gridSize+1):
        for col in range (1,gridSize+1):
            curr = cells[row][col]
            if curr.image in endPoints:
                up = cells[row-1][col].image
                down = cells[row+1][col].image
                left = cells[row][col-1].image
                right = cells[row][col+1].image
                nearbyEmptys = 0
                nearbyGoesIn = 0
                if up == empty:
                    nearbyEmptys +=1
                if down == empty:
                    nearbyEmptys +=1
                if left == empty:
                    nearbyEmptys +=1
                if right == empty:
                    nearbyEmptys +=1
                if up.goesDown == True:
                    nearbyGoesIn +=1
                if down.goesUp == True:
                    nearbyGoesIn +=1
                if left.goesRight == True:
                    nearbyGoesIn +=1
                if right.goesLeft == True:
                    nearbyGoesIn +=1
                if nearbyGoesIn >= 1:
                    return False
                elif nearbyGoesIn == 0 and nearbyEmptys == 0:
                    return False
                else:
                    return True
##def take(row, col):
##    global cells
##    global tileCount
##    curr = cells[row][col]
##    curr.config(image = tileList[tileCount])
##    curr.image = tileList[tileCount]
##    window.update_idletasks()
##    if checkCell(row, col) == True:
##        if checkEndpoint() == True:
##            if solve() == True:
##                tileCount = 0
##                return True
##    elif tileCount < len(tileList)-1:
##        tileCount += 1
##        take(row, col)
##    else:
##        curr.config(image = empty)
##        curr.image = empty
##        tileCount = 0
##        return False

def take(row, col):
    global cells
    for i in range(len(tileList)):
        cells[row][col].config(image = tileList[i])
        cells[row][col].image = tileList[i]
        window.update_idletasks()
        if checkCell(row, col) == True:
            if checkEndpoint() == True:
                if solve() == True:
                    return True
    cells[row][col].config(image = empty)
    cells[row][col].image = empty
    return False

def solve():
    global cells
    for row in range (1,gridSize+1):
        for col in range (1,gridSize+1):
            x = cells[row][col]
            if x.image == empty:
                if take(row, col) == True:
                    return True
                else:
                    return False
    return True
    
def testCase():
    global cells
    cells[2][2].config(image = endPoints[0]) #Blue
    cells[2][2].image = endPoints[0]
    cells[5][3].config(image = endPoints[0])
    cells[5][3].image = endPoints[0]
    cells[3][4].config(image = endPoints[1]) #Green
    cells[3][4].image = endPoints[1]
    cells[6][4].config(image = endPoints[1])
    cells[6][4].image = endPoints[1]
    cells[5][4].config(image = endPoints[2]) #Red
    cells[5][4].image = endPoints[2]
    cells[6][3].config(image = endPoints[2])
    cells[6][3].image = endPoints[2]
    cells[2][5].config(image = endPoints[3]) #Yellow
    cells[2][5].image = endPoints[3]
    cells[5][5].config(image = endPoints[3])
    cells[5][5].image = endPoints[3]

solveButton = Button(window, text = "Solve", command = solve)
solveButton.grid(row = 10, column = 1, columnspan = 3, sticky = "NSEW")

testButton = Button(window, text = "Test Case", command = testCase)
testButton.grid(row = 10, column = 4, columnspan = 3, sticky = "NSEW")

window.mainloop()

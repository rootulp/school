#Rootul Patel 10/4/13
#HW7 - Sound Editing

import BCAudio
from Tkinter import *

window = Tk()
window.title("Music Manipulator")

def read():
    song = songEntry.get()
    BCAudio.read(song)
    
def play():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    BCAudio.play()
    
def stop():
    BCAudio.stop()
    
def quiet():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    quietL = left[:]
    quietR = right[:]
    for i in range(len(left)):
        quietL[i] = left[i]/2.0
        quietR[i] = right[i]/2.0
    left[:] = quietL[:]
    right[:] = quietR[:]
    BCAudio.play()

def mono():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    mono = left[:]
    for i in range(len(left)):
        mono[i] = (left[i] + right[i])/ 2.0
    left[:] = mono[:]
    right[:] = mono[:]
    BCAudio.play()

def maximize():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    maxL = left[:]
    maxR = right[:]
    bigL = left[0]
    bigR = right[0]
    for i in range(len(left)):
        if abs(left[i]) > abs(bigL):
            bigL = left[i]
    for i in range(len(right)):
        if abs(right[i]) > abs(bigR):
            bigR = right[i]
    for i in range(len(left)):
        maxL[i] = left[i]/bigL
    for i in range(len(right)):
        maxR[i] = right[i]/bigR
    left[:] = maxL[:]
    right[:] = maxR[:]
    BCAudio.play()
            
def reverse():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    reverseL = []
    reverseR = []
    for i in reversed(right):
        reverseR.append(i)
    for i in reversed(left):
        reverseL.append(i)
    left[:] = reverseL[:]
    right[:] = reverseR[:]
    BCAudio.play()
    
def faster():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    leftFaster = []
    rightFaster = []
    for i in range(0,len(left),2):
        leftFaster.append(left[i])
    for i in range (0,len(right),2):
        rightFaster.append(right[i])
    left[:] = leftFaster[:]
    right[:] = rightFaster[:]
    BCAudio.play()
    
def louder():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    maxL = left[:]
    maxR = right[:]
    bigL = left[0]
    bigR = right[0]
    for i in range(len(left)):
        if abs(left[i]) > abs(bigL):
            bigL = left[i]
    for i in range(len(right)):
        if abs(right[i]) > abs(bigR):
            bigR = right[i]
    for i in range(len(left)):
        maxL[i] = left[i]/bigL
    for i in range(len(right)):
        maxR[i] = right[i]/bigR
    left[:] = maxL[:]
    right[:] = maxR[:]
    for i in range(len(left)):
        if left[i] > 0:
            left[i] = left[i] ** .8
        else:
            left[i] = -1*((left[i]*-1)**.8)
    for i in range(len(right)):
        if right[i] > 0:
            right[i] = right[i] ** .8
        else:
            right[i] = -1*((right[i]*-1)**.8)       
    BCAudio.play()

def distort():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    leftDistort = left[:]
    rightDistort = right[:]
    for i in range (len(left)):
        if left[i] >= .2:
            leftDistort[i] = .2
        elif left[i] <= -.2:
            leftDistort[i] = -.2
        else:
            leftDistort[i] = left[i]
    for i in range (len(right)):
        if right[i] >= .2:
            rightDistort[i] = .2
        elif right[i] <= -.2:
            rightDistort[i] = -.2
        else:
            rightDistort[i] = right[i]
    left[:] = leftDistort[:]
    right[:] = rightDistort[:]
    BCAudio.play()
    
def echo():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    leftEcho = []
    rightEcho = []
    leftTemp = []
    rightTemp = []
    delta = int(44100//8)
    for i in range (0, 44100//8):
        leftTemp.append(left[i])
        rightTemp.append(right[i])
    for i in range (44100//8,len(left)):
        leftEcho.append(.25*left[i] + .75*left[i-delta])
    for i in range (44100//8,len(right)):
        rightEcho.append(.25*right[i] + .75*right[i-delta])
    leftTemp.extend(leftEcho)
    rightTemp.extend(rightEcho)
    left[:] = leftTemp[:]
    right[:] = rightTemp[:]
    BCAudio.play()

def slower():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    leftSlow = []
    rightSlow = []
    for i in range (len(left)):
        leftSlow.append(left[i])
        leftSlow.append(left[i])
    for i in range (len(right)):
        rightSlow.append(right[i])
        rightSlow.append(right[i])
    left[:] = leftSlow[:]
    right[:] = rightSlow[:]
    BCAudio.play()
    
def swap():
    left = BCAudio.getLeft()
    right = BCAudio.getRight()
    newRight = left[:]
    newLeft = right[:]
    left[:] = newLeft[:]
    right[:] = newRight[:]
    BCAudio.play()
    
songEntry = Entry(window)
songEntry.grid(row = 0, column = 1, columnspan = 2, sticky = 'NSEW')

readButton = Button(window, text = "Read", command = read)
readButton.grid(row=0, column = 0, sticky = 'NSEW')

playButton = Button(window, text = "Play", command = play)
playButton.grid(row=0, column = 3, sticky = 'NSEW')

stopButton = Button(window, text = "Stop", command = stop)
stopButton.grid(row=0, column = 4, sticky = 'NSEW')

quietButton = Button(window, text = "Quiet", command = quiet)
quietButton.grid(row=1, column = 0, sticky = 'NSEW')

monoButton = Button(window, text = "Mono", command = mono)
monoButton.grid(row=1, column = 1, sticky = 'NSEW')

maximizeButton = Button(window, text = "Maximize", command = maximize)
maximizeButton.grid(row=1, column = 2, sticky = 'NSEW')

reverseButton = Button(window, text = "Reverse", command = reverse)
reverseButton.grid(row=1, column = 3, sticky = 'NSEW')

fasterButton = Button(window, text = "Faster", command = faster)
fasterButton.grid(row=1, column = 4, sticky = 'NSEW')

louderButton = Button(window, text = "Louder", command = louder)
louderButton.grid(row=2, column = 0, sticky = 'NSEW')

distortButton = Button(window, text = "Distort", command = distort)
distortButton.grid(row=2, column = 1, sticky = 'NSEW')

echoButton = Button(window, text = "Echo", command = echo)
echoButton.grid(row=2, column = 2, sticky = 'NSEW')

slowerButton = Button(window, text = "Slower", command = slower)
slowerButton.grid(row=2, column = 3, sticky = 'NSEW')

swapButton = Button(window, text = "Swap", command = swap)
swapButton.grid(row=2, column = 4, sticky = 'NSEW')

window.mainloop()

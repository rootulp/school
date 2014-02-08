# Stopwatch Gui, for HW#10
# A tester for the Stopwatch class
# Ames, Fall 2013

from Tkinter import *
from Stopwatch import *
import tkFont

stopWatches = 4# number of Stopwatches to create

window = Tk()
window.title("Multi Stopwatch")

bigFont = tkFont.Font(family="Helvetica", size=16)

for col in range(stopWatches):
    timeDisplay = Entry(window, justify=CENTER, font=bigFont, width=12)
    timeDisplay.grid(row=0, column=col, sticky="NSEW")
    stopWatch = Stopwatch(timeDisplay)
    StartButton = Button(window, text="Start", font=bigFont, command=stopWatch.start)
    StopButton  = Button(window, text="Stop",  font=bigFont, command=stopWatch.stop)
    ClearButton = Button(window, text="Clear", font=bigFont, command=stopWatch.clear)
    StartButton.grid(row=1, column=col, sticky="NSEW")
    StopButton. grid(row=2, column=col, sticky="NSEW")
    ClearButton.grid(row=3, column=col, sticky="NSEW")

window.mainloop()
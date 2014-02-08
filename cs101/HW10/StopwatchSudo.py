#Rootul Patel
#Stopwatch Sudo

import time
import thread

class Stopwatch:
    
    def __init__(self, timeDisplay):
        self.timeDisplay = timeDisplay
        self.time = 0
        self.timestr = ""

    def __str__(self):
        self.timeDisplay.delete(0, 100)
        self.timeDisplay.insert(0, self.timestr) 
        print self.timestr

    def start(self):
        self.time += 1
        self.timestr = str(self.time)
        return self.timestr

    def stop(self):
        self.time = self.time 
        return self.time

    def clear(self):
        self.time = 0
        return self.time
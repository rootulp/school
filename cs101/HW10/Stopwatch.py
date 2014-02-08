#Rootul Patel
#HW10 - Stopwatch
import thread
import time

class Stopwatch:
    def __init__(self, timeDisplay, currentTime =0, centiSeconds=0, seconds=0, minutes=0, hours=0,  display=0):
        self.timeDisplay=timeDisplay
        self.currentTime = 0
        self.centiSeconds = 0
        self.seconds = 0
        self.minutes = 0
        self.hours = 0
        self.display=0
    
    def start(self):
        thread.start_new_thread(self.count, (0,))

    def count(self, centiSeconds):
        self.current = "START"
        while self.current == "START":
            self.centiSeconds += 1
            time.sleep(.1)
            if self.centiSeconds == 10:
                self.seconds += 1
                self.centiSeconds =0
            if self.seconds == 60:
                self.minutes += 1
                self.seconds = 0
            if self.minutes == 60:
                self.hours += 1
                self.minutes = 0
            self.display = "%d:%d:%d.%d" % (self.hours,self.minutes,self.seconds,self.centiSeconds)
            self.timeDisplay.delete(0, 10)
            self.timeDisplay.insert(0, self.display)

    def stop(self):
        self.current = "STOP"
        self.currentTime = self.timeDisplay.get()
        self.timeDisplay.delete(0,10)
        self.timeDisplay.insert(0, self.currentTime)
        
    def clear(self):
        self.current = "CLEAR"
        self.timeDisplay.delete(0,10)
        self.currentTime = 0
        self.centiSeconds = 0
        self.seconds = 0
        self.minutes = 0
        self.hours = 0
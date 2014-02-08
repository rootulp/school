#Rootul Patel
#HW3 - Weather

import bcWeather
import time
from swampy.TurtleWorld import *
world = TurtleWorld()
turt = Turtle()
turt.delay = 0

def arrow(length, angle):
    lt(turt)
    rt(turt, angle)
    fd(turt, length)
    lt(turt, 135)
    fd(turt, 25)
    lt(turt, 180)
    fd(turt, 25)
    rt(turt, 90)
    fd(turt, 25)
    lt(turt, 180)
    fd(turt, 25)
    lt(turt, 135)    
    fd(turt, length)
    rt(turt, 180 - angle)
    rt(turt)

def weatherArrow(windSpeed, windDirection):
    arrow(20+20*windSpeed, windDirection)

def updateArrow():
    for i in range (10000):
        windSpeed = bcWeather.getWindSpeed()
        windDirection = bcWeather.getWindDirection()
        print "Wind Speed:",float(windSpeed)        
        print "Wind Direction:",float(windDirection)
        world.clear()
        weatherArrow(windSpeed, windDirection)
        time.sleep(1)

updateArrow()

#Rootul Patel 9/15/13
#HW3 - Weather

import bcWeather
import time
from swampy.TurtleWorld import *
world = TurtleWorld()
turt = Turtle()
turt.delay = 0

def arrow():                                                        #function to draw arrow
        windSpeed = bcWeather.getWindSpeed()        #get wind speed & direction
        windDirection = bcWeather.getWindDirection()
        print "Wind Speed:",float(windSpeed)             #print wind speed & direction
        print "Wind Direction:",float(windDirection)
        lt(turt,90.0-windDirection)                             #orient turtle and draw arrow
        fd(turt,20.0+20.0*windSpeed)                                
        lt(turt,135)
        fd(turt,30)
        lt(turt,180)
        fd(turt,30)
        rt(turt,90)
        fd(turt,30)
        rt(turt,180)
        fd(turt,30)
        lt(turt, 135)
        fd(turt,20.0+20.0*windSpeed)
        lt(turt,90.0+windDirection)
        
def repeatArrow():                                              #function to redraw arrow every second
    for i in range (1,10000):
        world.clear()
        arrow()
        time.sleep(1)
        
repeatArrow()                                                   #call repeatArrow() function

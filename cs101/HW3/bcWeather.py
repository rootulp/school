# Scrape weather data from Davis Instruments website, for the Boston College OR Newton feed
# William Ames, Fall 2013

import urllib
import re
import time
import random

last_web_update = 0 # a long time ago
pageText = ""

def getWebPage():
    global last_web_update
    global pageText
    if time.time() > last_web_update+10:  # update at every 10 seconds at most
        #page = urllib.urlopen("http://www.weatherlink.com/user/bcweather1/") # as of fall 2013, the last update at bc was 4/10/2013
        page = urllib.urlopen("http://www.weatherlink.com/user/gulottacomm/") # Gulotta Communications, Newton, MA
        pageText = page.read()
        page.close()
        last_web_update = time.time()
        #print "Web page updated at", time.time()

def getWindSpeed():
    """Returns the wind speed
    in miles per hour"""
    global pageText
    getWebPage()
    windSpeedPattern     = "Wind[^&]*&nbsp;([0-9]+)\\<"
    windDirectionPattern = "Wind[^\\>]*\\>[^\\>]*\\>[^\\>]*\\>[^\\>]*\\>[^\\>]*\\>([A-Za-z]+)(\\&nbsp;|\\<)"
    windSpeed     = float(re.search(windSpeedPattern,     pageText).group(1))
    windDirection = re.search(windDirectionPattern, pageText).group(1)
    if windDirection == "Calm":
        windSpeed = 0.0
    windSpeed += random.choice(range(-4,5))/10. # !! vary it a little (for CS1 purposes; shouldn't really be here)
    if (windSpeed<0):
        windSpeed=0 # prevent the random variation from making windSpeed negative
    #print "Wind Speed:", windSpeed
    return windSpeed

def getTemperature():
    global pageText
    getWebPage()
    temperaturePattern = "\\>(\\d+)\\<span\\ class=\\\"degrees\\\"\\>\\&deg\\;"
    temperature = float(re.search(temperaturePattern,   pageText).group(1))
    #print "Temperature:", temperature
    return temperature

def getPressure():
    global pageText
    getWebPage()
    pressurePattern = "Barometer[^0-9]+([0-9]+\\.[0-9]*)\\\""
    pressure = float(re.search(pressurePattern,      pageText).group(1))
    #print "Pressure:", pressure
    return pressure

def getHumidity():
    global pageText
    getWebPage()
    humidityPattern = "Humidity[^0-9]+([0-9]+)"
    humidity = float(re.search(humidityPattern,      pageText).group(1))
    #print "Humidity:", humidity
    return humidity

def getLastUpdate():
    global pageText
    getWebPage()
    lastUpdatePattern = "Current Conditions as of ([^\\<]+)\\<"
    lastUpdate = re.search(lastUpdatePattern,    pageText).group(1)
    #print "Last Update:", lastUpdate
    return lastUpdate

def getWindDirection():
    global pageText
    getWebPage()
    windDirectionPattern = "Wind[^\\>]*\\>[^\\>]*\\>[^\\>]*\\>[^\\>]*\\>[^\\>]*\\>([A-Za-z]+)(\\&nbsp;|\\<)"
    windDirectionStr = re.search(windDirectionPattern, pageText).group(1)
    directionDictionary = {"Calm":0,
                           "N":  0, "NNE": 23, "NE": 45, "ENE": 68,
                           "E": 90, "ESE":113, "SE":135, "SSE":158,
                           "S":180, "SSW":203, "SW":225, "WSW":248,
                           "W":270, "WNW":293, "NW":315, "NNW":338}
    
    windDirection = directionDictionary[windDirectionStr];
    windDirection += random.choice(range(-4,5)) # !! vary it a little (for CS1 purposes; shouldn't really be here)
    if windDirection < 0:
        windDirection += 360 # in case random variation took direction below zero
    #print "Wind Direction:", windDirection
    return windDirection

def testAll():
    print "Wind Speed:", getWindSpeed()
    print "Wind Direction:", getWindDirection()
    print "Temperature:", getTemperature()
    print "Pressure:", getPressure()
    print "Humidity:", getHumidity()
    print "Last Update:", getLastUpdate()

testAll()

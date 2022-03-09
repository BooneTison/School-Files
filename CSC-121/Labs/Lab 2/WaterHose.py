#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan 22 14:26:09 2019

@author: boonetison
"""
# WaterHose.py - Measuring how much water is pumping through a hose
# The user is asked to input: width, length, and water pressure
import math

# Input
widthInches = float(input("Enter the width of the hose, in inches: "))
lengthFeet = float(input("Enter the length of the hose, in feet: "))
pressure = float(input("Enter the water pressure, in gallons per second: "))

# Calculations
widthFeet = widthInches / 12 # Inches converted to feet
weightOunces = lengthFeet + widthFeet # Given in ounces
weightPounds = weightOunces * .0625 # Ounces converted to pounds

radius = widthFeet / 2 # Given in feet
volumeWater = math.pi * (radius * radius) * lengthFeet # Given in cubic feet

weightWater = volumeWater * 62.5 # Volume in cubic feet converted to pounds
weightHoseWithWaterPounds = weightPounds + weightWater # Total weight of the hose in pounds

hoseGallons = weightWater / 8 # Pounds converted to gallons
timeToFill = hoseGallons / pressure # Given in seconds

speedWater = lengthFeet / timeToFill # Given in feet per second

# Output
print ("") 
print ("Weight of the hose:                 {0:5.1f} pounds" .format(weightPounds)) 
print ("Weight of the hose with water:      {0:5.1f} pounds" .format(weightHoseWithWaterPounds))
print ("Speed of water inside hose:         {0:5.1f} feet per second" .format(speedWater))
print ("Time to fill hose:                  {0:5.1f} seconds" .format(timeToFill)) 
print ("")
print ("Thank you for using the Garden Hose program.")
print ("Good bye.") 
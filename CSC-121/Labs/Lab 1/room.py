#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan 15 15:12:38 2019

@author: boonetison
"""

# room.py - Calculate the square footage of a room and then the cost to paint the room
# The user will input the length, height, and width

# input
length = float(input("Please enter the length of the room: "))
width = float(input("Please enter the width of the room: "))
height = float(input("Please enter the height of the room: "))

# calculation
sqft = (2 * (length * height)) + (2 * (width * height))
cost = sqft * .10

# output
print ("The cost of painting a ", sqft, " square foot room is $", cost) 
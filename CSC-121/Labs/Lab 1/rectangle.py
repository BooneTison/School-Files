#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan 15 14:38:23 2019

@author: boonetison
"""

# rectangle.py - Calculating the perimeter and area of a rectangle
# The user is asked for the length and width 

# input
length = float(input("Please enter the rectangle's length:"))
width = float(input("Please enter the width:"))

# calculations
perimeter = 2 * ( length + width )
area = length * width

# output
print ("The area of the rectangle is ", area)
print ("And the perimeter is ", perimeter) 
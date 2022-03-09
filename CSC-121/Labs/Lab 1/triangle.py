#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan 15 14:57:10 2019

@author: boonetison
"""

# triangle.py - Calculating the hypotenuse and area of a right triangle 
# The user is asked to input two legs: a and b

# input
lega = float(input("Please enter the first leg of the right triangle: "))
legb = float(input("Please enter the second leg: "))

# calculations
hyp = ((lega*lega) + (legb*legb)) ** .5
area = (lega * legb) / 2

# output
print ("The hypotenuse of the triangle is ", hyp)
print ("The area of the triangle is ", area)

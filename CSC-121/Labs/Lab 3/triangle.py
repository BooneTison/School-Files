#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan 29 14:26:08 2019

@author: boonetison
"""

# triangle.py - The user is asked to input three variables
# The hypontenuse will be determined
# It will be determined if the triangle is obtuse, right or acute
# It will be determined if the triangle is equilateral, isosceles or scalene

# Input
first = float(input("Please enter the first side:   "))
second = float(input("Please enter the second side:  "))
third = float(input("Please enter the third side:   "))

# Calculate the longest side
if first >= second and first >= third:
    c = first
    a = second
    b = third
elif second >= first and second >= third:
    c = second
    a = first
    b = third
elif third >= first and third >= second:
    c = third
    a = first
    b = second
    
# Test if obtuse, right, or acute
angle = ''
if (c*c) > (a*a) + (b*b):
    angle = 'obtuse'
elif (c*c) == (a*a) + (b*b):
    angle = 'right'
elif (c*c) < (a*a) + (b*b):
    angle = 'acute' 

# Test if equilateral, isosceles, or scalene
length = ''
if a == b and b == c:
    length = 'equilateral'
elif a == b or b == c or a == c:
    length = 'isosceles'
else:
    length = 'scalene'
    

# Output
print ("")
print ("The angle of the triangle is", angle)
print ("The triangle is", length)

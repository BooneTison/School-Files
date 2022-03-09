#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb  5 14:25:11 2019

@author: boonetison
"""
# summation.py - Given a positive intger, 
# calculate she sum of the perfect squares up to n

# Input
needInput = True
while needInput:
    num = int(input("Please enter a positive integer: "))
    if num > 0:
        needInput = False
    else:
        print("Invalid number, please try again.")

# Calculations
total = 0
for i in range(1,num+1):
    total += (i*i)

# Output
print("The sum of the first", num, "perfect squares is", total)
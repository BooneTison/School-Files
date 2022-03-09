#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb  5 14:33:47 2019

@author: boonetison
"""
# summation2.py - Given a positive intger, 
# calculate the sum of 3i^2 - 2i + 1

# Input
num = int(input("Please enter a positive integer: "))

# Calculations
total = 0
for i in range(1,num+1):
    total += (3*(i*i)) - (2*i) + 1

# Output
print("The sum of the first", num, "polynomials is", total)
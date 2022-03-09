#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb 26 15:03:03 2019

@author: boonetison
"""
import re
# population.py - Given a 2-dimesional region, from an input file
# Calculate the centroid of population

# File
file = input("Enter the file you want to open: ")
inFile = open(file, "r")

# Calculations
weightedSumX = 0
weightedSumY = 0
weightSum = 0

for line in inFile:
    line = line[0:-1]
    tok = re.compile(" ")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len(s) > 0]
    
    x = float(tokenlist[0])
    y = float(tokenlist[1])
    weight = float(tokenlist[2])
    
    weightedSumX += x*weight
    weightedSumY += y*weight
    weightSum += weight
    
centroidX = weightedSumX / weightSum
centroidY = weightedSumY / weightSum

# Ouput
print("The centroid is ({0:4.2f}, {1:4.2f})." .format(centroidX,centroidY))
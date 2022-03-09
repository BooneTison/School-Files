#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr  2 14:21:40 2019

@author: boonetison
"""
# crossing.py
import re

found = False
while not found:
    try:
        fileName = input("Enter the file name: ")
        file = open(fileName)
        found = True
    except FileNotFoundError:
        print("Sorry, I cannot find the file", fileName)
        
count = 0
for line in file:
    numTrips = int(line[0:2])
    count += 1
    if count == 1:
        break

westOf = [ "MN" , "IA" , "MO" , "AR" , "LA" ]
eastOf = [ "WI" , "IL" , "KY" , "TN" , "MS" ]

currentState = ""
currentDir = ""
oldDir = ""
cross = 0
letsprint = False
n = 0
count  = 0
for line in file:
    line = line.strip()
    if "Trip" in line:
        letsprint = True
        if letsprint and count > 0 and cross != 1:
            print("Trip",n,"crosses the Mississippi",cross,"times.")
        elif letsprint and count > 0 and cross == 1:
            print("Trip",n,"crosses the Mississippi",cross,"time.")
        cross = 0
        tok = re.compile("[ .]")
        tokenlist = tok.split(line)
        tokenlist = [ s for s in tokenlist if len(s) > 0 ]
        n = int(tokenlist[1])
        numCities = tokenlist[3]
        count += 1
        currentDir = ""
    if "," in line:
        letsprint = False
        tok = re.compile("[ ,]")
        tokenlist = tok.split(line)
        tokenlist = [ s for s in tokenlist if len(s) > 0 ]
        currentState = tokenlist[-1]
        oldDir = currentDir
        if currentState in westOf:
            currentDir = "West"
        elif currentState in eastOf:
            currentDir = "East"

        if oldDir != currentDir and oldDir != "":
            cross += 1
if cross != 1:
    print("Trip",n,"crosses the Mississippi",cross,"times.")
elif cross == 1:
    print("Trip",n,"crosses the Mississippi",cross,"time.")
file.close()
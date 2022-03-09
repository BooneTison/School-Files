#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 23 14:03:17 2019

@author: boonetison
"""
# triangleisland.py

import re

file = open("island.txt")

count = 0
for line in file:
    if count == 0:
        count += 1
        continue
    
    tok = re.compile("[ ]")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len(s) > 0 ]
    longDistance = int(tokenlist[0])
    latDistance = int(tokenlist[1])
    hotelDistance = int(tokenlist[2])
    hypDistance = (longDistance ** 2 + latDistance ** 2) ** .5
    
    west = hotelDistance + longDistance
    east = (latDistance - hotelDistance) + hypDistance
    if west < east:
        print(count,"go west")
    else:
        print(count,"go east")
    
    count += 1

file.close()  
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 16 14:27:48 2019

@author: boonetison
"""
# villageGong.py 
import re
filename = "gong.txt"
file = open(filename)
for line in file:
    if ":" not in line:
        continue
    tok = re.compile("[ :]")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len(s) > 0 ]
    hour = int(tokenlist[0])
    minute = int(tokenlist[1])
    second = int(tokenlist[2])
    
    if minute % 10 == 0 and second == 0:
        time = 0
        print(time)
    elif minute % 10 == 9:
        time = 60 - second
        print(time)
    else:
        time = (9 - (minute % 10)) * 60 + (60 - second)
        print(time)

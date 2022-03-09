#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 12 14:55:00 2019

@author: boonetison
"""
# olympics.py - Given a lsit of host cities for the summer olympics
# Given a year, return the city
import re
success = False
while not success:
    try:
        fileName = input("What is the file name? ")
        file = open(fileName)
        success = True
    except FileNotFoundError:
        print("File not found, try again")
        
D = {}
for line in file:
    tok = re.compile(" ")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len(s) > 0]
    year = int(tokenlist[0])
    city = tokenlist[1]
    D[year] = city
 
inputyear = int(input("Enter a year: "))
if inputyear in D:
    print("The host in",inputyear,"was",D[inputyear])
else:
    print("I have no data on that year.")
    
file.close
        
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb 12 14:24:38 2019

@author: boonetison
"""
# check.py - Given a string
# See if the string is 6 characters long and contains a lower case t

# Input
s = input("Please enter a string with 6 characters, containing the letter 't': ")

# Check
goodLen = False
containT = False
locationT = 0
if len(s) == 6:
    goodLen = True
if s.count('t') > 0:
    locationT = s.find('t') 
    containT = True
    
# Output
if goodLen == False:
    print("Sorry, your string has a length of", len(s))
    
if containT:
    print("There is a 't' located at position", locationT)
else:
    print("Your string does not contain a 't'!")
    


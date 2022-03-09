#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb 12 14:34:04 2019

@author: boonetison
"""
# vowel.py - Given a string
# Count the number of vowels

# Input
s = input("Please enter a string: ")

# Calculations
vowels = "aeiou" 
count = 0 # count of vowels in string
sTest = s.lower() # variable with the string in the lower case ofr simplified testing
for i in range (0, len(sTest)): # cycles through each character of string...
    if sTest[i] in vowels: # checking if the character is a vowel...
        count += 1 # and if so, the count is added to by one

# Output
if count == 0:
    print("There are no vowels in the string!")
elif count == 1:
    print("There is 1 vowel in the string.")
else:
    print("There are", count ,"vowels in the string.")


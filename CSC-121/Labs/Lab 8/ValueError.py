#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 12 14:26:42 2019

@author: boonetison
"""
# ValueError.py - Given a positive integer
# Print out the square of the integer
# Also error check

working = False
while not working:
    try:
        n = int(input("Enter a positive integer: "))
        if n > 0:
            working = True
        else:
            print("Your number is not positive. Try again.")
    except:
        print("Sorry, I don't understand your input. Try again.")
        
answer = n * n
print("Your number squared is",answer)

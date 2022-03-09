#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb 12 14:56:17 2019

@author: boonetison
"""
# game2.py - Given a word
# Make a game where the user must enter a word with the same first letter
# As the previous word ended with

# Initial input
sOld = ""
sNew = input("Enter a word: ")

# Continued input
i = 1
while i < 2: # Play for up to five times
    # Input
    sOld = sNew
    sNew = input("Enter a word which first letter is the same letter as the previous word: ")
    
    # Check
    if sOld[-1] != sNew[0]:
        print("The first letter of", sNew ,"does not match the last letter of", sOld)
        print("GAME OVER!")
        i += 1
    
# End
print()
print("Thanks for playing!")
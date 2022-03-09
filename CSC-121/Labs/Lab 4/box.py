#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb  5 14:38:16 2019

@author: boonetison
"""
# box.py - Given two integers, rows and columns, both greater than 3
# Draw a rectangle made of asterisks

# Input
rows = int(input("Please enter the number of rows(3+):    "))
columns = int(input("Please enter the number of columns(3+): "))

# Solid
print("Solid rectangle:")
for i in range(0,rows):
    for j in range(0,columns):
        print("*", end = "")
    print()
    
print()

# Hollow
print("Hollow rectangle:")
for i in range(1,rows+1):
    for j in range(1,columns+1):
        if i == 1 or i == rows or j == 1 or j == columns:
            print("*", end = "")
        else:
            print(" ", end = "")
    print()
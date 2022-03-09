#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb  5 15:10:35 2019

@author: boonetison
"""
# triangle.py - Given a positive integer
# Draw a right triangle

# Input
num = int(input("Please enter a positive integer: "))

# Drawing
for i in range (1,num+1):
    for j in range (1,num+1):
        if j <= i:
            print("*", end = "")
        else:
            print(" ", end = "")
    print()
    
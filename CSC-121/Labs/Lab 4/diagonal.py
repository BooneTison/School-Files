#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb  5 14:56:06 2019

@author: boonetison
"""
# diagonal.py - Given a number, preferablly odd and greater than 3
# Draw a big letter x n wide

# Input
num = int(input("Please enter a positive integer: "))

# Drawing
for i in range (1,num+1):
    for j in range (1,num+1):
        if i == j or i+j == num+1:
            print("*", end = "")
        else:
            print(" ", end  = "")
    print()
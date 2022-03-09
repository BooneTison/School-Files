#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb 26 14:24:27 2019

@author: boonetison
"""

# primes.py - Given a large positive prime number
# Find all prime numbers between 1 and n, inclusive
# And output these numbers to a text file

# File 
outFile = open("primeOutput.txt", "w")

# Input
finNum = int(input("Please enter a large positive integer: "))
count = 0
n = 2

# Calculations
while n <= finNum:
    if n % 10000 == 0:
        print(n)
    prime = True
    for i in range(2,int(n ** .5)+1):
        if n % i == 0:
            prime = False
            break
    if prime:
        count += 1
        outFile.write(str(n) + "\n")
    n += 1

# Calculations
print("There are",count ,"prime numbers between 1 and",finNum)

# File
outFile.close()
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb 19 14:27:00 2019

@author: boonetison
"""
# stat.py - Given a list of numbers
# Calculate the mean, standard deviation,  and median

# Input
L = []
num = float(input("Please enter a number, enter -1 to stop: "))
if num != -1:
    L.append(num)
while num != -1:
    num = float(input("Please enter a number, enter -1 to stop: "))
    if num != -1:
        L.append(num)

# Calculate mean
meanL = sum(L) / len(L)

# Calculate standard deviation
dev = []
for n in L:
    nDev = n - meanL
    nDev = nDev ** 2
    dev.append(nDev)
avgDev = sum(dev) / len(dev)
stanDev = avgDev ** .5

# Calculate median
L.sort()
if len(L) % 2 != 0:
    medianLoc = len(L) // 2
    median = L[medianLoc]
else:
    medianLoc = len(L) // 2
    medianLoc2 = medianLoc - 1
    median = (L[medianLoc] + L[medianLoc2]) / 2
    
# Calculate 3 largest numbers
largest1 = L[-1]
largest2 = L[-2]
largest3 = L[-3]

# Output
print()
print("Your numbers are:")
for i in range (0,len(L)):
    if i != len(L)-1:
        print(L[i], end = ", ")
    else:
        print(L[i], end = " ")
print()
print("The mean is {0:6.2f}" .format(meanL))
print("The standard deviation is {0:6.2f}" .format(stanDev))
print("The median is {0:6.2f}" .format(median))
print("The 3 largest numbers are {0:5.2f}, {1:5.2f}, and {2:5.2f}" .format(largest1,largest2,largest3))
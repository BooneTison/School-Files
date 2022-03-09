#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 19 14:47:47 2019

@author: boonetison
"""
# functionloops.py

# Functions
def fun1(x):
    return (3*x**2 - 2*x + 1)

def fun2(x):
    return x ** -x

# Main program
for i in range(1,11):
    print(i,fun1(i))

print()

max_i = 0.01
max_value = fun2(0.01)
for i in range(1,101):
    print(i/100,fun2(i/100))
    if fun2(i/100) > max_value:
        max_i = i/100
        max_value = fun2(i/100)
    
print()    
print("The max was at",max_i,"and the max value was",max_value)
    
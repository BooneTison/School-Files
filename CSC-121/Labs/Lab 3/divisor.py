#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan 29 14:54:38 2019

@author: boonetison
"""

# divisor.py - Given a number, determine if it is prime

# Input
n = int(input("Please enter a positive integer:    "))

# Count the number of proper divisors.
count = 0
divisor = 1
added = 0
definition = ''
while divisor < n:
    if n % divisor == 0:
        count += 1
        added += divisor
    divisor += 1

if added > n:
    definition = 'abundant'
elif added == n:
    definition = 'perfect'
elif added < n:
    definition = 'deficient'

# Output
print("")
if count == 1:
    print("Prime number")
else:
    print ("NOT a prime number")
print("The sum of the divisors is", added)
print("The sum of proper divisors is", definition)
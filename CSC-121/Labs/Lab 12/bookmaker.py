#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 16 14:46:44 2019

@author: boonetison
"""
# bookmaker.py
import re
filename = "bookmaker.txt"
file = open(filename)
count = 0
sumpay = 0
for line in file:
    line = line.strip()
    if "$" not in line:
        continue
    count += 1
    tok = re.compile("[ ,$]")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len(s) > 0 ]
    amount = int(tokenlist[0])
    if tokenlist[1] == "even":
        bettype = "even"
    else:
        odd1 = int(tokenlist[1])
        odd2 = int(tokenlist[3])
        if tokenlist[-1] == "on":
            bettype = "on"
        else:
            bettype = "against"
            
    if bettype == "on":
        payout = (1 + (odd2 / odd1)) * amount
    elif bettype == "against":
        payout = (1 + (odd1 / odd2)) * amount
    elif bettype == "even":
        payout = 2 * amount
    sumpay += amount
    
    print("Bet",count,"payout is $ {0:7.2f}" .format(payout))
    
profit = sumpay * .09
print("Expected bookmaker profit is $ {0:7.2f}" .format(profit))
        
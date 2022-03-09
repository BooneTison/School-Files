#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 12 14:34:35 2019

@author: boonetison
"""
# ValueErrorToken.py - Given a line of text, with both words and numbers
# Add up all the real numbers
import re
line = input("Enter a line with both words and numbers: ")
total = 0.0
add = 0.0

tok = re.compile("[ .,]")
tokenlist = tok.split(line)
tokenlist = [ s for s in tokenlist if len(s) > 0]
for c in tokenlist:
    try:
        add = float(c)
        total += add
    except ValueError:
        continue
    
print("The total is", total)

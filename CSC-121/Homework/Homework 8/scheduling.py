#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Apr 22 19:16:25 2019

@author: boonetison
"""
# scheduling.py - Given a list of times on a schedule M-F from 7-7
# Determine when a one hour meeting can be scheduled
import re

def timetoindex(n):
    if n >= 7:
        n -= 7
        return n
    else:
        n += 5
        return n

def printtimes(L,day):
    s = ""
    if True in L:
        print(day, end = " ")
        for i in range(0,len(L)):
            if L[i] == True:
                if i <= 5:
                    s += str(i+7)
                    s += ","
                else:
                    s += str(i-5)
                    s += ","
        print(s[0:-1])
            
try:
    fileName = input("Enter the file name: ")
    file = open(fileName)
except FileNotFoundError:
    print("Sorry, I cannot find the file", fileName)
    
M = [True,True,True,True,True,True,True,True,True,True,True,True]
T = [True,True,True,True,True,True,True,True,True,True,True,True]
W = [True,True,True,True,True,True,True,True,True,True,True,True]
R = [True,True,True,True,True,True,True,True,True,True,True,True]
F = [True,True,True,True,True,True,True,True,True,True,True,True]
for line in file:
    tok = re.compile("[ -]")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len(s) > 0 ]
    
    if "M" in tokenlist[0]:
        start = int(tokenlist[1])
        end = int(tokenlist[2])
        start = timetoindex(start)
        if end == 7: 
            end = 12
        else:
            end = timetoindex(end)
        for i in range(start,end):
            M[i] = False
    if "T" in tokenlist[0]:
        start = int(tokenlist[1])
        end = int(tokenlist[2])
        start = timetoindex(start)
        if end == 7: 
            end = 12
        else:
            end = timetoindex(end)
        for i in range(start,end):
            T[i] = False
    if "W" in tokenlist[0]:
        start = int(tokenlist[1])
        end = int(tokenlist[2])
        start = timetoindex(start)
        if end == 7: 
            end = 12
        else:
            end = timetoindex(end)
        for i in range(start,end):
            W[i] = False
    if "R" in tokenlist[0]:
        start = int(tokenlist[1])
        end = int(tokenlist[2])
        start = timetoindex(start)
        if end == 7: 
            end = 12
        else:
            end = timetoindex(end)
        for i in range(start,end):
            R[i] = False 
    if "F" in tokenlist[0]:
        start = int(tokenlist[1])
        end = int(tokenlist[2])
        start = timetoindex(start)
        if end == 7: 
            end = 12
        else:
            end = timetoindex(end)
        for i in range(start,end):
            F[i] = False   
            
if True in M or True in T or True in W or True in R or True in F:
    print("Here are the possible meeting times:")
    printtimes(M,"M")
    printtimes(T,"T")
    printtimes(W,"W")
    printtimes(R,"R")      
    printtimes(F,"F")  
else:
    print("There are no possible meeting times.")     
            
file.close()
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Apr  7 16:42:43 2019

@author: boonetison
"""
import re
# givingDirections.py - Given two addresses
# In a city with streets east to west, 1 in east to 100 west,
# and avenues south to north, 1 in south to 100 north.
# Each block has addresses 99 addresses, (101-199) 
# with odd on the west and south and even on the east and north
# Each address will have three attributes: 
# an address number in the range [101..9999], 
# a road number in the range [1..100], 
# and a road type which will be either “Street” or “Avenue”.
# Output the shortest route using the fewest number of roads
# there are various cases that you need to consider when forming the route. 
# For example, the two addresses could be on the same road, 
# on perpendicular roads, or on parallel roads
# Avenues - North = decreasing, south = increasing
# Streets - East = decreasing, west = increasing

try:
    fileName = input("Enter the file name: ")
    file = open(fileName)
except FileNotFoundError:
    print("Sorry, I cannot find the file", fileName)
    
for line in file:
    tok = re.compile("[ .]")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len(s) > 0 ]
    tokenlist = tokenlist[5:]
    firstadd = int(tokenlist[0])
    firstroad = int(tokenlist[1])
    firsttype = tokenlist[2]
    secondadd = int(tokenlist[4])
    secondroad = int(tokenlist[5])
    secondtype = tokenlist[6]
    
    # Same road
    if firstroad == secondroad and firsttype == secondtype:
        if firsttype == "Street":
            if firstadd > secondadd:
                print("Go east on",firstroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            else:
                print("Go west on",firstroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")  
                print()
        elif firsttype == "Avenue":            
            if firstadd > secondadd:
                print("Go south on",firstroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            else:
                print("Go north on",firstroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.") 
                print()
    # Parallel roads
    elif firsttype == secondtype:
        if firsttype == "Street":
            if firstroad > secondroad and firstadd // 100 == secondadd // 100: # Going south and same block
                avenuetoturn = secondadd // 100
                if secondadd % 100 > 50 and firstadd % 100 > 50:
                    avenuetoturn += 1
                    print("Go west on",firstroad,"Street.")
                    print("Go south on",avenuetoturn,"Avenue.")
                    print("Go east on",secondroad,"Street.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your left.")
                    else:
                        print(secondadd,"will be on your right.")
                    print()
                elif secondadd % 100 <= 50 and firstadd % 100 <= 50:
                    print("Go east on",firstroad,"Street.")
                    print("Go south on",avenuetoturn,"Avenue.")
                    print("Go west on",secondroad,"Street.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your right.")
                    else:
                        print(secondadd,"will be on your left.")
                    print()
                else:
                    if firstadd%100 + secondadd%100 <= (100-firstadd%100) + (100-secondadd%100):
                        print("Go east on",firstroad,"Street.")
                        print("Go south on",avenuetoturn,"Avenue.")
                        print("Go west on",secondroad,"Street.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your right.")
                        else:
                            print(secondadd,"will be on your left.")
                        print()
                    else:
                        avenuetoturn += 1
                        print("Go west on",firstroad,"Street.")
                        print("Go south on",avenuetoturn,"Avenue.")
                        print("Go east on",secondroad,"Street.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your left.")
                        else:
                            print(secondadd,"will be on your right.")
                        print()
            elif firstroad < secondroad and firstadd // 100 == secondadd // 100: # Going north and same block
                avenuetoturn = secondadd // 100
                if secondadd % 100 > 50 and firstadd % 100 > 50:
                    avenuetoturn += 1
                    print("Go west on",firstroad,"Street.")
                    print("Go north on",avenuetoturn,"Avenue.")
                    print("Go east on",secondroad,"Street.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your left.")
                    else:
                        print(secondadd,"will be on your right.")
                    print()
                elif secondadd % 100 <= 50 and firstadd % 100 <= 50:
                    print("Go east on",firstroad,"Street.")
                    print("Go north on",avenuetoturn,"Avenue.")
                    print("Go west on",secondroad,"Street.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your right.")
                    else:
                        print(secondadd,"will be on your left.")
                    print()
                else:
                    if firstadd%100 + secondadd%100 <= (100-firstadd%100) + (100-secondadd%100):
                        print("Go east on",firstroad,"Street.")
                        print("Go north on",avenuetoturn,"Avenue.")
                        print("Go west on",secondroad,"Street.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your right.")
                        else:
                            print(secondadd,"will be on your left.")
                        print()
                    else:
                        avenuetoturn += 1
                        print("Go west on",firstroad,"Street.")
                        print("Go north on",avenuetoturn,"Avenue.")
                        print("Go east on",secondroad,"Street.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your left.")
                        else:
                            print(secondadd,"will be on your right.")
                        print()
            elif firstroad > secondroad and firstadd > secondadd: # Going south and address is east 
                avenuetoturn = secondadd // 100
                avenuetoturn += 1
                print("Go east on",firstroad,"Street.")
                print("Go south on",avenuetoturn,"Avenue.")
                print("Go east on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad > secondroad and firstadd < secondadd: # Going south and address is west
                avenuetoturn = secondadd // 100
                print("Go west on",firstroad,"Street.")
                print("Go south on",avenuetoturn,"Avenue.")
                print("Go west on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
            elif firstroad < secondroad and firstadd > secondadd: # Going north and address is east 
                avenuetoturn = secondadd // 100
                avenuetoturn += 1
                print("Go east on",firstroad,"Street.")
                print("Go north on",avenuetoturn,"Avenue.")
                print("Go east on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad < secondroad and firstadd < secondadd: # Going north and address is west
                avenuetoturn = secondadd // 100
                print("Go west on",firstroad,"Street.")
                print("Go north on",avenuetoturn,"Avenue.")
                print("Go west on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
            
        elif firsttype == "Avenue":
            if firstroad > secondroad and firstadd // 100 == secondadd // 100: # Going east and same block
                streettoturn = secondadd // 100
                if secondadd % 100 > 50 and firstadd % 100 > 50:
                    streettoturn += 1
                    print("Go north on",firstroad,"Avenue.")
                    print("Go east on",streettoturn,"Street.")
                    print("Go south on",secondroad,"Avenue.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your left.")
                    else:
                        print(secondadd,"will be on your right.")
                    print()
                elif secondadd % 100 <= 50 and firstadd % 100 <= 50:
                    print("Go south on",firstroad,"Avenue.")
                    print("Go east on",streettoturn,"Street.")
                    print("Go north on",secondroad,"Avenue.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your right.")
                    else:
                        print(secondadd,"will be on your left.")
                    print()
                else:
                    if firstadd%100 + secondadd%100 <= (100-firstadd%100) + (100-secondadd%100):
                        print("Go south on",firstroad,"Avenue.")
                        print("Go east on",streettoturn,"Street.")
                        print("Go north on",secondroad,"Avenue.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your right.")
                        else:
                            print(secondadd,"will be on your left.")
                        print()
                    else:
                        streettoturn += 1
                        print("Go north on",firstroad,"Avenue.")
                        print("Go east on",streettoturn,"Street.")
                        print("Go south on",secondroad,"Avenue.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your left.")
                        else:
                            print(secondadd,"will be on your right.")
                        print()
            elif firstroad < secondroad and firstadd // 100 == secondadd // 100: # Going west and same block
                streettoturn = secondadd // 100
                if secondadd % 100 > 50 and firstadd % 100 > 50:
                    streettoturn += 1
                    print("Go north on",firstroad,"Avenue.")
                    print("Go west on",streettoturn,"Street.")
                    print("Go south on",secondroad,"Avenue.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your left.")
                    else:
                        print(secondadd,"will be on your right.")
                    print()
                elif secondadd % 100 <= 50 and firstadd % 100 <= 50:
                    print("Go south on",firstroad,"Avenue.")
                    print("Go west on",streettoturn,"Street.")
                    print("Go north on",secondroad,"Avenue.")
                    if secondadd % 2 == 0:
                        print(secondadd,"will be on your right.")
                    else:
                        print(secondadd,"will be on your left.")
                    print()
                else:
                    if firstadd%100 + secondadd%100 <= (100-firstadd%100) + (100-secondadd%100):
                        print("Go south on",firstroad,"Avenue.")
                        print("Go west on",streettoturn,"Street.")
                        print("Go north on",secondroad,"Avenue.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your right.")
                        else:
                            print(secondadd,"will be on your left.")
                        print()
                    else:
                        streettoturn += 1
                        print("Go north on",firstroad,"Avenue.")
                        print("Go west on",streettoturn,"Street.")
                        print("Go south on",secondroad,"Avenue.")
                        if secondadd % 2 == 0:
                            print(secondadd,"will be on your left.")
                        else:
                            print(secondadd,"will be on your right.")
                        print()
            elif firstroad > secondroad and firstadd > secondadd: # Going east and address is south 
                streettoturn = secondadd // 100
                streettoturn += 1
                print("Go south on",firstroad,"Avenue.")
                print("Go east on",streettoturn,"Street.")
                print("Go south on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad > secondroad and firstadd < secondadd: # Going east abd address is north
                streettoturn = secondadd // 100
                print("Go north on",firstroad,"Avenue.")
                print("Go east on",streettoturn,"Street.")
                print("Go north on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
            elif firstroad < secondroad and firstadd > secondadd: # Going west and address is south 
                streettoturn = secondadd // 100
                streettoturn += 1
                print("Go south on",firstroad,"Avenue.")
                print("Go west on",streettoturn,"Street.")
                print("Go south on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad < secondroad and firstadd < secondadd: # Going west and address is north
                streettoturn = secondadd // 100
                print("Go north on",firstroad,"Avenue.")
                print("Go west on",streettoturn,"Street.")
                print("Go north on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
            
    # Perpendicular roads (BOO)
    elif firsttype != secondtype:
        if secondtype == "Street": # Going from avenue to street
            startstreet = firstadd // 100
            endavenue = secondadd // 100
            if firstroad >= endavenue and startstreet >= secondroad: # Going east and south
                print("Go south on",firstroad,"Avenue.")
                print("Go east on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad >= endavenue and startstreet < secondroad: # Going east and north
                print("Go north on",firstroad,"Avenue.")
                print("Go east on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad < endavenue and startstreet >= secondroad: # Going west and south
                print("Go south on",firstroad,"Avenue.")
                print("Go west on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
            elif firstroad < endavenue and startstreet < secondroad: # Going west and north
                print("Go north on",firstroad,"Avenue.")
                print("Go west on",secondroad,"Street.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
        elif secondtype == "Avenue": # Going from street to avenue
            startavenue = firstadd // 100
            endstreet = secondadd // 100
            if firstroad >= endstreet and startavenue >= secondroad: # Going south and east
                print("Go east on",firstroad,"Street.")
                print("Go south on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad >= endstreet and startavenue < secondroad: # Going south and west
                print("Go west on",firstroad,"Street.")
                print("Go south on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your left.")
                else:
                    print(secondadd,"will be on your right.")
                print()
            elif firstroad < endstreet and startavenue >= secondroad: # Going north and east
                print("Go east on",firstroad,"Street.")
                print("Go north on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
            elif firstroad < endstreet and startavenue < secondroad: # Going north and west
                print("Go west on",firstroad,"Street.")
                print("Go north on",secondroad,"Avenue.")
                if secondadd % 2 == 0:
                    print(secondadd,"will be on your right.")
                else:
                    print(secondadd,"will be on your left.")
                print()
                
file.close()
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Apr 27 19:18:00 2019

@author: boonetison
"""
# Players earn +1 whenever their team scores a goal, and –1 each time the opposing team scores
# power play goals do not contribute to any player’s plus/minus rating 
# goals scored by a team that is equally matched or shorthanded do count for plus/minus.
# goalies are not evaluated according to plus/minus
# The attributes of a player will be the name, position, (jersey) number, and the plus/minus rating. 
# You also need to keep track of the names of the two teams.
# When your program starts, it should open an input file called hockey.txt. 
# This file will summarize a single hockey game. 
# The beginning of the file will mention which teams are playing. The visiting team is displayed first. 
# The rest of the file will describe the “goal” events.
# Each time there is a goal, you will see which team scored, what kind of goal it was, 
# and which players on both teams were on the ice at the time of the goal
import re


file = open("hockey.txt")
count = 0
V = {}
H = {}
numgoals = 0
readv = False
for line in file:
    line.strip()
    if count == 0:
        visit = line[0:3]
        home = line[11:14]
        count += 1
        continue
    
    if "scored goal" in line:
        scoringteam = line[0:3]
        if "Power Play" in line:
            powerplay = True
        elif "Shorthanded" in line:
            powerplay = False
        else:
            powerplay = False
            numgoals += 1
        continue
        
    if "On the ice" in line and visit in line:
        readv = False
        continue
    if "On the ice" in line and home in line:
        readv = True
        continue
    
    if not readv and "(" in line:
        tok = re.compile("[ ()]")
        tokenlist = tok.split(line)
        tokenlist = [ s for s in tokenlist if len(s) > 0 ]
        if "Wing" in line:
            pos = tokenlist[0] + " " + tokenlist[1]
            tokenlist.remove(tokenlist[0])
            tokenlist.remove(tokenlist[0])
            tokenlist.remove(tokenlist[0])
            try:
                num = str(int(tokenlist[-1]))
            except:
                tokenlist.remove(tokenlist[-1])
                num = tokenlist[-1]
            tokenlist.remove(tokenlist[-1])
            name = tokenlist[0]
            for i in range(1,len(tokenlist)):
                name += " " + tokenlist[i]
        else:
            pos = tokenlist[0]
            tokenlist.remove(tokenlist[0])
            tokenlist.remove(tokenlist[0])
            try:
                num = str(int(tokenlist[-1]))
            except:
                tokenlist.remove(tokenlist[-1])
                num = tokenlist[-1]
            tokenlist.remove(tokenlist[-1])
            name = tokenlist[0]
            for i in range(1,len(tokenlist)):
                name += " " + tokenlist[i]
        if pos == "Goalie":
            continue
        
        if name not in V:
            V[name] = [pos,num,0,False]
        
        if not powerplay and scoringteam == visit:
            V[name][2] += 1
            V[name][3] = True
        elif not powerplay and scoringteam == home:
            V[name][2] -= 1
            V[name][3] = True
            
    elif readv and "(" in line:
        tok = re.compile("[ ()]")
        tokenlist = tok.split(line)
        tokenlist = [ s for s in tokenlist if len(s) > 0 ]
        if "Wing" in line:
            pos = tokenlist[0] + " " + tokenlist[1]
            tokenlist.remove(tokenlist[0])
            tokenlist.remove(tokenlist[0])
            tokenlist.remove(tokenlist[0])
            try:
                num = str(int(tokenlist[-1]))
            except:
                tokenlist.remove(tokenlist[-1])
                num = tokenlist[-1]
            tokenlist.remove(tokenlist[-1])
            name = tokenlist[0]
            for i in range(1,len(tokenlist)):
                name += " " + tokenlist[i]
        else:
            pos = tokenlist[0]
            tokenlist.remove(tokenlist[0])
            tokenlist.remove(tokenlist[0])
            try:
                num = str(int(tokenlist[-1]))
            except:
                tokenlist.remove(tokenlist[-1])
                num = tokenlist[-1]
            tokenlist.remove(tokenlist[-1])
            name = tokenlist[0]
            for i in range(1,len(tokenlist)):
                name += " " + tokenlist[i]
        
        if pos == "Goalie":
            continue
        
        if name not in H:
            H[name] = [pos,num,0,False]
            
        if not powerplay and scoringteam == home:
            H[name][2] += 1
            H[name][3] = True
        elif not powerplay and scoringteam == visit:
            H[name][2] -= 1
            H[name][3] = True
            

    
            
# Output
print("Visiting team",visit)
for i in range(numgoals,-numgoals-1,-1):
    for key in V:
        if V[key][2] == i and V[key][3] == True:
            print("#{0:>2s} {1:20s} {2:10s} rating: {3:>2d}" .format(V[key][1],key,V[key][0],V[key][2]))
print()
print("Home team",home)
for i in range(numgoals,-numgoals-1,-1):
    for key in H:
        if H[key][2] == i and H[key][3] == True:
            print("#{0:>2s} {1:20s} {2:10s} rating: {3:>2d}" .format(H[key][1],key,H[key][0],H[key][2]))

            
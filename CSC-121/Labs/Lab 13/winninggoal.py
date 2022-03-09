#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 23 14:07:51 2019

@author: boonetison
"""
# winninggoal.py
import re
file = open("goal.txt")

count = -1
team1Score = []
team2Score = []
team1 = ""
team2 = ""
winninggoal = ""
toprint = "Game "
for line in file:
    if count == -1:
        count += 1
        continue
    if "Game" in line:
        if count > 0:
            toprint = "Game "
            if len(team1Score) > len(team2Score):
                index = len(team2Score)
                winninggoal = team1Score[index]
                toprint += str(count) + ", "
                toprint += team1 + " " + str(len(team1Score)) + " "
                toprint += team2 + " " + str(len(team2Score)) + ", "
                toprint += "winning goal by " + winninggoal
                print(toprint)
            else:
                index = len(team1Score)
                winninggoal = team2Score[index]
                toprint += str(count) + ", "
                toprint += team2 + " " + str(len(team2Score)) + " "
                toprint += team1 + " " + str(len(team1Score)) + ", "
                toprint += "winning goal by " + winninggoal
                print(toprint)
        count += 1
        line = line.strip()
        tok = re.compile("[ ]")
        tokenlist = tok.split(line)
        tokenlist = [ s for s in tokenlist if len(s) > 0 ]
        team1 = tokenlist[2]
        team2 = tokenlist[4]
        team1Score = []
        team2Score = []
    elif "*" in line:
        toprint = "Game "
        if len(team1Score) > len(team2Score):
            index = len(team2Score)
            winninggoal = team1Score[index]
            toprint += str(count) + ", "
            toprint += team1 + " " + str(len(team1Score)) + " "
            toprint += team2 + " " + str(len(team2Score)) + ", "
            toprint += "winning goal by " + winninggoal
            print(toprint)
        else:
            index = len(team1Score)
            winninggoal = team2Score[index]
            toprint += str(count) + ", "
            toprint += team2 + " " + str(len(team2Score)) + " "
            toprint += team1 + " " + str(len(team1Score)) + ", "
            toprint += "winning goal by " + winninggoal
            print(toprint)
    else:
        line = line.strip()
        tok = re.compile("[ ]")
        tokenlist = tok.split(line)
        tokenlist = [ s for s in tokenlist if len(s) > 0 ]
        scoreteam = tokenlist[0]
        scoreplayer = tokenlist[2] + " " + tokenlist[3]
        
        if scoreteam == team1:
            team1Score.append(scoreplayer)
        else:
            team2Score.append(scoreplayer)
file.close()
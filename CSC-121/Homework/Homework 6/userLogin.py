#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Mar 24 13:51:56 2019

@author: boonetison
"""
# userLogin.py - Given a text file containing data on login sessions
# Ask the user for the data file name and a username
# Calculate the total time logged in for each user and output for a specific user
import re

# File 
fileName = input("Which file has the login data? ")
file = open(fileName, "r")

login = {} 
# Parse the file
for line in file:
    if "(" not in line or "still logged in" in line: # Skip currently logged in users and reboots
        continue
    
    username = line[:8] # First 8 characters contain usernames
    tok = re.compile(" ")
    tokenlistuser = tok.split(username)
    tokenlistuser = [ s for s in tokenlistuser if len(s) > 0] # Remove excess spaces in usernames
    username = tokenlistuser[0]
    
    if "+" in line: # For times over days
        value = line.find("(")
        timeString = line[value:]
        tok = re.compile("[()+: ]")
        tokenlisttime = tok.split(timeString)
        tokenlisttime = [ s for s in tokenlisttime if len(s) > 0]
        days = int(tokenlisttime[0])
        hours = int(tokenlisttime[1])
        minutes = int(tokenlisttime[2])
        total = (days * 24 * 60) + (hours * 60) + minutes
        
        if username in login:
            login[username] += total
        else:
            login[username] = total
    else:
        value = line.find("(")
        timeString = line[value:]
        tok = re.compile("[(): ]")
        tokenlisttime = tok.split(timeString)
        tokenlisttime = [ s for s in tokenlisttime if len(s) > 0]
        hours = int(tokenlisttime[0])
        minutes = int(tokenlisttime[1])
        total = (hours * 60) + minutes
    
        if username in login:
            login[username] += total
        else:
            login[username] = total

# Input user
print()
print("Which users would you like to look up? Enter names one at a time.")
print("Simply hit enter to quit.")
print()

inputUser = "default"
while len(inputUser) > 0:
    inputUser = input("Enter a username:  ")
    if inputUser in login:
        print(inputUser,"has",login[inputUser],"minutes of login time.")
    elif len(inputUser) > 0:
        print("Sorry, I have no data on",inputUser)
    else:
        break

print("Good bye!")
file.close()
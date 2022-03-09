#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 19 14:28:18 2019

@author: boonetison
"""
# finance,py - Using functions, perform three financial compuations

# Functions
def compound(pv,n,i):
    i = i / 100
    interest = pv * ((1 + i) ** n)
    return interest

def annuity(amount,n,i):
    i = i / 100
    value = amount * (((1+i)**n - 1)/ i)
    return value

def loan(pv,n,i):
    i = i / 1200
    n = (n * 12) * -1
    payment = pv * (i/(1-(1+i)**n))
    return payment

# Main program
# Calculations
accountGrowth = compound(1000,10,7)
investedMoney = annuity(19000,30,3)
loanPayment = loan(100000,15,5)

# Output
print("The account will grow to $ {0:.2f}" .format(accountGrowth)) 
print("The 401(k) will be valued at $ {0:.2f}" .format(investedMoney))
print("The loan payment is $ {0:.2f}" .format(loanPayment))

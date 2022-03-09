#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec  1 14:18:56 2021

@author: boonetison
"""
# Remove NA data: df = df.dropna()
# Normalize numeric: X_normalized = X[:].apply(lambda x: (x -min(x))/(max(x)-min(x)))
# Dicretize nominal: y_discretized = np.where(y > y.median(),"good","bad")
# One Hot Encoding: one_hot_data = pd.get_dummies(lenses[['Age']],drop_first=True)
# Drop column: reg.drop("Movie", axis=1)

# Remove NA for all
# Classification: Discretize gross, normalize numeric
# Regression: One Hot nominal

# Linear regression (Variable selection?)
# KNN classification
# KNN regression
# Regression tree
# Decision tree

import pandas as pd
import numpy as np
reg = pd.read_excel("Final Training Data.xlsx")
clss = pd.read_excel("Final Training Data.xlsx")

#reg = reg.dropna()
reg['Budget'].fillna(1,inplace = True)
#clss = clss.dropna()
clss['Budget'].fillna(1,inplace = True)

reg = reg.drop("Movie", axis=1)
clss = clss.drop("Movie", axis=1)

# for regression data
one_hot_reg = pd.get_dummies(reg[['Distributor','Genre','Rating','Country']],drop_first=True)
frames = [reg[['Theatres','Budget','IMDB','Run Time']],one_hot_reg]
final_reg = pd.concat(frames, axis=1)

# for classification data
numeric = clss[['Theatres','Budget','IMDB','Run Time']]
numeric_normalized = numeric[:].apply(lambda x: (x -min(x))/(max(x)-min(x)))
gross = clss['Gross']
gross_discretized = np.where(gross > gross.median(),"good","bad")
frames = [clss[['Distributor','Genre','Rating','Country']],numeric_normalized]
final_clss = pd.concat(frames, axis=1)

y_reg = reg['Gross']
y_clss = gross_discretized

# Worst - < 10,626.75
# Poor - < 427,808
# Good - < 9,779,562.50
# Best - > 9,779,562.50


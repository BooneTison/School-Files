#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec  2 09:45:26 2021

@author: Boone Tison and Jackson King
"""
import pandas as pd
from sklearn.neighbors import KNeighborsRegressor
from sklearn.metrics import mean_absolute_error
import matplotlib.pyplot as plt

# Input data
reg = pd.read_excel("Final Training Data.xlsx")

# Remove rows with missing values
reg = reg.dropna()

# Remove unneeded rows
reg = reg.drop("Movie", axis=1)
reg = reg.drop("Nominal Gross", axis=1)

# Normalize the numeric attributes
numeric = reg[['Theatres','Budget','IMDB','Run Time']]
numeric_normalized = numeric[:].apply(lambda x: (x -min(x))/(max(x)-min(x)))

# One hot encode nominal values
one_hot_clss = pd.get_dummies(reg[['Distributor','Genre','Rating','Country']],drop_first=True)
frames = [numeric_normalized,one_hot_clss]
final_clss = pd.concat(frames, axis=1)

# Set X and y
X = final_clss
y = reg[['Gross']]

# Set up test and training data
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]

# Set up model
knn = KNeighborsRegressor(n_neighbors=3)
knn.fit(X_train,y_train)
pred = knn.predict(X_test)
for p in pred:
    print(p)

# Print results
print("Accuracy: " + str(knn.score(X_train,y_train)))
print("Mean Absoulute Error: " + str(mean_absolute_error(y_test, pred)))

# Test different neighborhood sizes
sizes = {}
results = []
for i in range(1,len(X_train.index)):
    knn = KNeighborsRegressor(n_neighbors=i)
    knn.fit(X_train,y_train)
    pred = knn.predict(X_test)
    sizes[i] = "{:.3f}".format(knn.score(X_train,y_train))
    results.append([i,knn.score(X_train,y_train)])
print(sizes)

# Plot neighborhood size results
results=pd.DataFrame(results,columns=["k","accuracy"])
plt.plot(results.k,results.accuracy)
plt.title("Value of k and corresponding regression accuracy")
plt.show() 
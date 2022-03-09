#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec  2 09:25:58 2021

@author: Boone Tison and Jackson King
"""
import pandas as pd
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import classification_report,confusion_matrix, accuracy_score
import matplotlib.pyplot as plt

# Input data
clss = pd.read_excel("Final Training Data.xlsx")

# Remove rows with missing values
clss = clss.dropna()

# Remove unneeded rows
clss = clss.drop("Movie", axis=1)
clss = clss.drop("Gross", axis=1)

# Normalize the numeric attributes
numeric = clss[['Theatres','Budget','IMDB','Run Time']]
numeric_normalized = numeric[:].apply(lambda x: (x -min(x))/(max(x)-min(x)))

# One hot encode nominal values
one_hot_clss = pd.get_dummies(clss[['Distributor','Genre','Rating','Country']],drop_first=True)
frames = [numeric_normalized,one_hot_clss]
final_clss = pd.concat(frames, axis=1)

# Set X and y
X = final_clss
y = clss[['Nominal Gross']]

# Set up test and training data
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]

# Set up model
knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(X_train,y_train)
pred = knn.predict(X_test)
for p in pred:
    print(p)

# Print results
print(accuracy_score(y_test,pred))
print(confusion_matrix(y_test,pred))
print(classification_report(y_test,pred))

# Test different neighborhood sizes
sizes = {}
results = []
for i in range(1,len(X_train.index)):
    knn = KNeighborsClassifier(n_neighbors=i)
    knn.fit(X_train,y_train)
    pred = knn.predict(X_test)
    sizes[i] = "{:.3f}".format(accuracy_score(y_test,pred))
    results.append([i,knn.score(X_train,y_train)])
print(sizes)

# Plot neighborhood size results
results=pd.DataFrame(results,columns=["k","accuracy"])
plt.plot(results.k,results.accuracy)
plt.title("Value of k and corresponding Classification accuracy")
plt.show() 
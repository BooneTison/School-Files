#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec  1 21:27:32 2021

@author: Boone Tison and Jackson King
"""
import pandas as pd
import statsmodels.api as sm
import numpy as np
from sklearn.metrics import mean_absolute_error

# Input data
reg = pd.read_excel("Final Training Data.xlsx")

# Remove rows with missing values
reg = reg.dropna()

# Remove unneeded rows
reg = reg.drop("Movie", axis=1)
reg = reg.drop("Nominal Gross", axis=1)

# One hot encode nominal values
one_hot_reg = pd.get_dummies(reg[['Distributor','Genre','Rating','Country']],drop_first=True)
frames = [reg[['Theatres','Budget','IMDB','Run Time']],one_hot_reg]
final_reg = pd.concat(frames, axis=1)

# Set x and y
X = final_reg
y = reg['Gross']

# Set training and test data
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]

# Create model
Xcon = sm.add_constant(X_train, has_constant='add')
lr_model = sm.OLS(y_train, Xcon).fit()

# Print model
print(lr_model.summary())
print(lr_model.params)

# Predict values
testDataCon = sm.add_constant(X_test, has_constant='add')
prediction = lr_model.predict(testDataCon)
for p in prediction:
    print(f'{p:.2f}')
print("Mean Absolute Error: " + str(mean_absolute_error(y_test, prediction)))
    
# Test out a model using only the originally numeric attributes
X = reg[['Theatres','Budget','IMDB','Run Time']]
y = reg['Gross']

# Set training and test data
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]

# Create model
Xcon = sm.add_constant(X_train, has_constant='add')
lr_model = sm.OLS(y_train, Xcon).fit()

# Print model
print(lr_model.summary())
print(lr_model.params)

# Predict values
testDataCon = sm.add_constant(X_test, has_constant='add')
prediction = lr_model.predict(testDataCon)
for p in prediction:
    print(f'{p:.2f}')
print("Mean Absolute Error: " + str(mean_absolute_error(y_test, prediction)))
    
# Test out a model using only one hot encoded nominal attributes
X = pd.get_dummies(reg[['Distributor','Genre','Rating','Country']],drop_first=True)
y = reg['Gross']

# Set training and test data
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]

# Create model
Xcon = sm.add_constant(X_train, has_constant='add')
lr_model = sm.OLS(y_train, Xcon).fit()

# Print model
print(lr_model.summary())
print(lr_model.params)

# Predict values
testDataCon = sm.add_constant(X_test, has_constant='add')
prediction = lr_model.predict(testDataCon)
for p in prediction:
    print(f'{p:.2f}')
print("Mean Absolute Error: " + str(mean_absolute_error(y_test, prediction)))
    
# All attributes: 0.741 R-squared
# All numeric: 0.459 R-squared
# Remove Run Time: 0.438 R-squared
# Remove IMDB: 0.448 R-squared
# Remove Budget: 0.374 R-squared
# Remove Theatres: 0.436 R-Squared
# All nominal: 0.539 R-Squared
# Remove Country: 0.538 R-Squared
# Remove Rating: 0.534 R-Squared
# Remove Genre: 0.525 R-Squared
# Remove Distributor: 0.251 R-Squared

# Print out all the correalation coefficients
columns = list(final_reg) # Create a list of column names
for c in columns:
    print("Correlation of Gross & " + c + " = ", np.corrcoef(reg.Gross, final_reg[c]) [0,1])
# Correlation higher than +-0.5:
# Theatres, Budget
# Important from R-Squared: 
# Distributor

# Try a model with these important attributes
one_hot_reg = pd.get_dummies(reg[['Distributor']],drop_first=True)
frames = [reg[['Theatres','Budget']],one_hot_reg]
final_reg = pd.concat(frames, axis=1)

# Set x and y
X = final_reg
y = reg['Gross']

# Set training and test data
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]

# Create model
Xcon = sm.add_constant(X_train, has_constant='add')
lr_model = sm.OLS(y_train, Xcon).fit()

# Print model
print(lr_model.summary())
print(lr_model.params)

# Predict values
testDataCon = sm.add_constant(X_test, has_constant='add')
prediction = lr_model.predict(testDataCon)
for p in prediction:
    print(f'{p:.2f}')
print("Mean Absolute Error: " + str(mean_absolute_error(y_test, prediction)))
# R-Squared: 0.666
# Add back IMDB: 0.667
# Add back Run Time: 0.676
# Add back IMDB and Run Time: 0.677
# All numeric plus Dist: 0.677
# Add in Genre: 0.695
# Add in Rating: 0.689
# Add in Country: 0.707
# Genre + Rating: 0.706
# Genre + Country: 0.739
# Rating + Country: 0.709
# All attributes: 0.741

# Conclusion:
# all of the attributes improve the model's r-squared

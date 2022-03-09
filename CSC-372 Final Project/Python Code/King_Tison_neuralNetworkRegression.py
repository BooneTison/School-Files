# -*- coding: utf-8 -*-
"""

@author: Jackson King and Boone Tison
"""

# mlp for regression
from numpy import sqrt
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense
import pandas as pd

# load the dataset
df = pd.read_excel("Final Training Data.xlsx")
df.tail()
df.head()

# Remove unneeded attributes
df = df.drop("Movie", axis=1)
df = df.drop("Nominal Gross", axis=1)

# Remove rows with missing values
df = df.dropna()

# One hot encode nominal values as numeric
one_hot_reg = pd.get_dummies(df[['Distributor','Genre','Rating','Country']],drop_first=True)
frames = [df[['Theatres','Budget','IMDB','Run Time']],one_hot_reg]
final_reg = pd.concat(frames, axis=1)

# Set up X and y
X = final_reg
y = df[['Gross']]

#Making the training and testing set
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]
print(X_train.shape, X_test.shape, y_train.shape, y_test.shape)

# determine the number of input features
n_features = X_train.shape[1]

# define model
model = Sequential()
model.add(Dense(50, activation='relu', kernel_initializer='he_normal', input_shape=(n_features,)))
model.add(Dense(40, activation='relu', kernel_initializer='he_normal'))
model.add(Dense(30, activation='relu', kernel_initializer='he_normal'))
model.add(Dense(20, activation='relu', kernel_initializer='he_normal'))
model.add(Dense(10, activation='relu', kernel_initializer='he_normal'))
model.add(Dense(8, activation='relu', kernel_initializer='he_normal'))
model.add(Dense(6, activation='relu', kernel_initializer='he_normal'))
model.add(Dense(5, activation='relu', kernel_initializer='he_normal'))
model.add(Dense(1))  # default or linear activation function (no function)

# compile the model
model.compile(optimizer='adam', loss='mse')
# fit the model
model.fit(X_train, y_train, epochs=150, batch_size=32, verbose=0)

# evaluate the model
error = model.evaluate(X_test, y_test, verbose=0)
print('MSE: %.3f, RMSE: %.3f' % (error, sqrt(error)))
# off by about $5-6K

# make a prediction
yhat = model.predict([X_test])    # result of softmax probability calculation
for p in yhat:
    print(p)
    
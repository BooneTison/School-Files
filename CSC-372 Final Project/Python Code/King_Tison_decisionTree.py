#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec  2 13:48:08 2021

@author:Boone Tison and Jackson King
"""
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import confusion_matrix, accuracy_score, classification_report
from sklearn import tree

# Input data
clss = pd.read_excel("Final Training Data.xlsx")

# Remove rows with missing values
clss = clss.dropna()

# Remove unneeded rows
clss = clss.drop("Movie", axis=1)
clss = clss.drop("Gross", axis=1)

# One hot encode nominal values
one_hot_clss = pd.get_dummies(clss[['Distributor','Genre','Rating','Country']],drop_first=True)
frames = [clss[['Theatres','Budget','IMDB','Run Time']],one_hot_clss]
final_clss = pd.concat(frames, axis=1)

# Set X and y
X = final_clss
y = clss[['Nominal Gross']]

# Making the training and testing set
X_train = X.drop([116,117,118,119,120,121], axis=0)
y_train = y.drop([116,117,118,119,120,121], axis=0)
X_test = X.iloc[-6:]
y_test = y.iloc[-6:]

# Making an object of the Classifier
clf = DecisionTreeClassifier(criterion='entropy').fit(X_train, y_train)

# Predicting on the test set
predictions = clf.predict(X_test)
for p in predictions:
    print(p)
print("Accuracy:", accuracy_score(y_test, predictions))
print(confusion_matrix(y_test,predictions))
print(classification_report(y_test,predictions))

# Visualizing the decision tree
fn=['Theatres',	'Budget',	'IMDB',	'Run Time',		'Distributor_Abramorama Films',	'Distributor_Arclight Films',	'Distributor_Argot Pictures',	'Distributor_Broad Green Pictures',	'Distributor_CBS Films',	'Distributor_Cinedigm',	'Distributor_Cinema Guild',	'Distributor_Cleopatra Films',	'Distributor_Cohen Media Group',	'Distributor_Eros Entertainment',	'Distributor_FilmDistrict',	'Distributor_FilmRise',	'Distributor_First Run Features',	'Distributor_Focus Features',	'Distributor_Freestyle Releasing',	'Distributor_GKIDS',	'Distributor_IFC Films',	'Distributor_Indican Pictures',	'Distributor_Kino Lorber',	'Distributor_Lionsgate',	'Distributor_MGM',	'Distributor_Magnolia Pictures',	'Distributor_Mongrel Media',	'Distributor_Music Box Films',	'Distributor_Open Road',	'Distributor_Oscilloscope Pictures',	'Distributor_Paramount Pictures	', 'Distributor_Pathe Contemporary Films',	'Distributor_Phase 4 Films',	'Distributor_Rainbow Releasing',	'Distributor_Reliance Big Pictures',	'Distributor_Roadside Attractions',	'Distributor_Rocket Releasing', 	'Distributor_STX Entertainment',	'Distributor_Sony Pictures',	'Distributor_Strand',	'Distributor_Summit Entertainment',	'Distributor_UTV Communications',	'Distributor_United Artists',	'Distributor_Universal',	'Distributor_Walt Disney',	'Distributor_Warner Bros.',	'Distributor_Weinstein Co.',	'Genre_Adventure',	'Genre_Animation',	'Genre_Biography',	'Genre_Comedy',	'Genre_Crime',	'Genre_Documentary',	'Genre_Drama',	'Genre_Horror',	'Rating_PG',	'Rating_PG-13',	'Rating_R',	'Country_Canada',	'Country_France',	'Country_Germany',	'Country_India',	'Country_Ireland',	'Country_Israel',	'Country_Norway',	'Country_Portugal',	'Country_South Korea',	'Country_Spain',	'Country_Sweden',	'Country_Turkey',	'Country_UK',	'Country_USA']
cn=['[Gross=Best]','Gross=Good]','[Gross=Poor]','Gross=Worst]']
fig, axes = plt.subplots()
tree.plot_tree(clf,
                feature_names = fn, 
                class_names=cn,
                filled = True);

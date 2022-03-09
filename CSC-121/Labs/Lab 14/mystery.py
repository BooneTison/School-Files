import re
import random

name = input("Please enter file name:  ")
file = open(name, "r")
wordlist = []

for line in file:
    line = line[:-1]
    tok = re.compile(" ")
    tokenlist = tok.split(line)
    tokenlist = [ s for s in tokenlist if len (s) > 0 ]
    wordlist += tokenlist
file.close()

# Now we have all the words in the document
#print(wordlist)

d = {}
for i in range(0, len(wordlist)-2):
    word1 = wordlist[i]
    word2 = wordlist[i+1]
    word3 = wordlist[i+2]
    if (word1, word2) in d:
        d[(word1, word2)].append(word3)
    else:
        d[(word1, word2)] = [word3]

#for pair in d:
#    print(pair, len(d[pair]))

numPairs = len(d)

# Pick a random pair to start with.
pairNumber = random.randint(1, numPairs)
i = 0
for pair in d:
    i += 1
    if i == pairNumber:
        startingPair = pair
        break

# Create list of words containing output.
output = []
output.append(startingPair[0])
output.append(startingPair[1])
pair = startingPair

for i in range(0, 100):
    # pick a random word from this pair's list of 3rd word possibilities
    j = random.randint(0, len(d[pair]) - 1)
    word3 = d[pair][j]
    output.append(word3)
    pair = (pair[1], word3)

wordnum = 0
for word in output:
    print(word, end=" ")
    wordnum += 1
    if wordnum % 10 == 0:
        print()
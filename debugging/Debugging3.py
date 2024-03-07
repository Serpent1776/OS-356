''' This program finds the highest and second highest test scores out of 5
scores entered.  The prompt said not to use lists, so the person is solving
it with just variables.  The program almost always works, but if the 5 scores
are 100, 90, 80, 70, 60, in that order, then it doesn't work.'''

initialScore = eval(input('Enter first test score: '))
highest = initialScore
secondHighest = float('-inf')


for i in range (4):
    nextScore = eval(input('Enter next test score: '))

    if nextScore > highest:
        secondHighest = highest
        highest = nextScore
    elif nextScore > secondHighest:
        secondHighest = nextScore

print ('Highest score: ', highest)
print ('Second highest score: ', secondHighest)

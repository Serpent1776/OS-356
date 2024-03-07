''' This is a simple guess-a-number game that is supposed to limit players to
3 guesses, but a bug allows them to keep guessing.'''

from random import randint

secret = randint(1, 100)
guesses_left = 3
right = False
while guesses_left > 0 or not right:
    guess = int(input('Enter your guess: '))
    if guess > secret:
        print('Lower')
    elif guess < secret:
        print('Higher')
    else:
        print('Right!')
        right = True
    guesses_left -= 1
    if(guesses_left == 0):
        print('The correct number was', secret)
        break

''' This is a slider puzzle.  When you click on a cell next to the open
spot, it's supposed to slide that piece into the open slot.  The goal is
to try to get all the cells in order.  However, sometimes, when clicking
on a cell, it disappears instead of sliding.'''

from tkinter import * 
from random import shuffle
from tkinter.messagebox import showinfo

def function(x,y):
    if x<=2 and states[x+1][y]==0:
        x1,y1 = x+1,y
    elif x>=1 and states[x-1][y]==0:
        x1,y1 = x-1,y
    elif y<=2 and states[x][y+1]==0:
        x1,y1 = x,y+1
    elif y>=1 and states[x][y-1]==0:
        x1,y1 = x,y-1
    else:
        return
    
    buttons[x1][y1].configure(text=states[x][y])
    buttons[x][y].configure(text="")
    states[x][y], states[x1][y1] = states[x1][y1] , states[x][y]#swaps using a,b=b,a
         
    if states==final:
        showinfo(title="Winner", message="Yay you won!")
            

root = Tk()
root.title("Joey's Slider Puzzle")

buttons = [[0,0,0,0],
           [0,0,0,0],
           [0,0,0,0],
           [0,0,0,0]]

L = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
shuffle(L)


states = [[L[0],L[1],L[2],L[3]],
          [L[4],L[5],L[6],L[7]],
          [L[8],L[9],L[10],L[11]],
           [L[12],L[13],L[14],L[15]]]
    

for i in range(4):
    for j in range(4):
        buttons[i][j] = Button(font = ("Copperplate gothic bold", 56), bg="green", width=3, 
                               command = lambda x=i,y=j: function(x,y))
        if states[i][j] != 0:
            buttons[i][j].configure(text = states[i][j])
        buttons[i][j].grid(row = i, column = j)

final=[[1,2,3,4],
       [5,6,7,8],
       [9,10,11,12],
       [13,14,15,0]]
        



mainloop()


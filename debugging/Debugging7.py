''' This is a Tic-tac-toe like game where you're trying to get 3 in a row
vertically, horizontally, or diagonally.  The twist is that both players
use the same symbol and the winner is the first one to get 3 in a row
anywhere.  However, there is a bug where sometimes the game ends earlier
than it should, after just 2 in a row.Z'''

from tkinter import *

def check_for_winner():
    if num_cols>=3:
        for r in range(num_rows):
            for c in range(num_cols-2):
                if board[r][c]==board[r][c+1]==board[r][c+2]==1:
                    return [(r,c), (r,c+1), (r,c+2)]
    if num_rows>=3:            
        for c in range(num_cols):
            for r in range(num_rows-2):
                if board[r][c]==board[r+1][c]==board[r+1][c]==1:
                    return [(r,c), (r+1,c), (r+2,c)]
    
    if num_cols>=3 and num_rows>=3:
        for r in range(num_rows-2):
            for c in range(num_cols-2):
                if board[r][c]==board[r+1][c+1]==board[r+2][c+2]==1:
                    return [(r,c), (r+1,c+1), (r+2,c+2)]

        for r in range(num_rows-2):
            for c in range(2,num_cols):
                if board[r][c]==board[r-1][c-1]==board[r-2][c-2]==1:
                    return [(r,c), (r-1,c-1), (r-2,c-2)]        

    return []

def callback(r,c):
    global board, player, game_over, prev_move
    if board[r][c]==0 and not game_over:
        board[r][c] = 1
        b[r][c].create_oval(10,10,40,40, fill='black')
        if prev_move != ():
            b[prev_move[0]][prev_move[1]].delete(ALL)
            b[prev_move[0]][prev_move[1]].create_oval(10,10,40,40, fill='black')
        
        prev_move = (r,c)
        text = b[r][c].create_text(26,25, text=str(player), fill='white')
        L = check_for_winner()
        if L != []:
            game_over = True
            for (r,c) in L:
                b[r][c].configure(bg='white')
                b[r][c].create_text(26,25, text=str(player), fill='white')
        player = 1 if player==2 else 2

def restart():
    global num_rows, num_cols, board, b, board_frame, player, game_over, prev_move

    for i in range(num_rows):
        for j in range(num_cols):
            b[i][j].destroy()

    num_rows = int(num_rows_entry.get())
    num_cols = int(num_cols_entry.get())
    board = [[0]*num_cols for i in range(num_rows)]
    player = 1
    game_over = False
    prev_move = ()
    set_up_board()

def set_up_board():
    global b, board_frame
    b = [[0]*num_cols for i in range(num_rows)]
    for i in range(num_rows):
        for j in range(num_cols):
            b[i][j] = Canvas(board_frame, width=50, height=50, bg='#ffff55')
            b[i][j].bind('<Button-1>', lambda e,r=i,c=j: callback(r,c))
            b[i][j].grid(row = i, column = j)

root = Tk()

player = 1
game_over = False
prev_move = ()
num_rows = 4
num_cols = 4
board = [[0]*num_cols for i in range(num_rows)]
b = [[0]*num_cols for i in range(num_rows)]

board_frame = Frame()
set_up_board()

restart_button = Button(text='Restart', command = restart)

row_col_frame = Frame()
row_col_label = Label(row_col_frame, text='x')
num_rows_entry = Entry(row_col_frame, width=2)
num_cols_entry = Entry(row_col_frame, width=2)
num_rows_entry.insert(0, '4')
num_cols_entry.insert(0, '4')

num_rows_entry.grid(row=0, column=0)
row_col_label.grid(row=0, column=1)
num_cols_entry.grid(row=0, column=2)

board_frame.grid(row=0)
row_col_frame.grid(row=2)
restart_button.grid(row=3)

mainloop()

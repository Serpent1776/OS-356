'''The Mount’s radio station WMTB 89.9 FM is running a special promotion where
if you buy two items totaling exactly $89.90 using their app, then you get
both for free. They programmed the app using the Python code below. But
someone could buy something for $57.91 and something else for $31.99
and the program will not correctly catch that as totaling $89.90.
Find and fix the problem.'''

price1 = float(input('Enter price of first item: '))
price2 = float(input('Enter price of second item: '))
if round((price1 + price2)*100)/100 == 89.90:
    print('Everything\'s free!!!')
else:
    print('Total cost is {:.2f}'.format(price1 + price2))

from threading import *
def f():
    lock.acquire()
    for i in range(1000000000000, 2000000000000):
        for d in range(2, int(i**.5)+2):    
            if i % d == 0:
                break
        else:    
            print(i)
    lock.release()       

def g():
    lock.acquire()
    for i in range(2000000000000, 3000000000000):
        for d in range(2, int(i**.5)+2):    
            if i % d == 0:
                break
        else:    
            print(i)
            i+=1
    lock.release()                 
            

lock = Lock()
t1 = Thread(target = f)
t2 = Thread(target = g)

t1.start()
t2.start()

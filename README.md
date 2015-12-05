# SpinLocks
Project for algorithms course. 
Multiple threads all compete to update a shared counter. 
Needed to implement the counters lock as a spin lock.

Base Lock holds the basic lock interface

Implements an ArrayLock, TTASLock and BakeryLock
Each type is created and tested. The output and performance of each lock is then printed with different amounts of threads. 

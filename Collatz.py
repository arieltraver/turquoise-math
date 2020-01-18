import math


def collatz(n, counter):
    #print(n)
    if int(math.log(n, 2)) == math.log(n, 2):
        print(f"Total is {counter + math.log(n, 2)}")
        return counter + math.log(n, 2)
    else:
        while n % 2 == 0:
            n /= 2
            #print(n)
            counter += 1
        #print("Jumped")
        #print(3 * n + 1)
        collatz((3 * n + 1) / 2, counter + 2)


collatz(837799,1)

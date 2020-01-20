import math


def collatz(n, counter):
    """
    Counts the terms in a collatz sequence for any number.
    Once it hits a power of two, no more odds will appear, so it multiplies by log2 of the current term."""
    if int(math.log(n, 2)) == math.log(n, 2):
        print(f"Total is {counter + math.log(n, 2)}")
        return counter + math.log(n, 2)
    else:
        while n % 2 == 0:
            n /= 2
            counter += 1
        collatz((3 * n + 1) / 2, counter + 2)


collatz(837799, 1)

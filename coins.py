import math


def comb2(n):
    total = (n - n % 2) / 2
    return total + 1


def comb5(n):
    """How many ways are there to make (n) pence with 5p, 2p, and 1p coins?"""
    total = 0
    for i in range(0, math.ceil(n / 5)):
        total += comb2(n - 5 * i)
    if n % 5 == 0:
        total += 1
    print(f"Comb5 of {n} is {total}")
    return total


def comb10(n):
    """How many ways are there to make (n) pence with 10p, 5p, 2p, and 1p coins?"""
    total = 0
    for i in range(0, math.ceil(n / 10)):
        total += comb5(n - 10 * i)
    if n % 10 == 0:
        total += 1
    print(f"comb10 of {n} is {total}")
    return total


def comb20(n):
    """How many ways are there to make (n) pence with 20p, 10p, 5p, 2p, and 1p coins?"""
    total = 0
    for i in range(0, math.ceil(n / 20)):
        total += comb10(n - 20 * i)
    if n % 20 == 0:
        total += 1
    return total


def comb50(n):
    """How many ways are there to make (n) pence with 50p, 20p, 10p, 5p, 2p, and 1p coins?"""
    total = 0
    for i in range(0, math.ceil(n / 50)):
        total += comb20(n - 50 * i)
    if n % 50 == 0:
        total += 1
    return total


def comb100(n):
    """How many ways are there to make (n) pence with 100p, 50p, 20p, 10p, 5p, 2p, and 1p coins?"""
    total = 0
    for i in range(0, math.ceil(n / 100)):
        total += comb50(n - 100 * i)
    if n % 100 == 0:
        total += 1
    return total


print(comb100(200))
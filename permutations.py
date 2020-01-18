import math


def find_swaps(num, n):
    """
    Returns the switches made to the first lexicographic permutation, in order to get the desired permutation.
    Param:
    num(list): a list of all the numbers, in the very first order, such as [1, 2, 3, 4]
    n(integer): the lexicographic permutation you're looking for. """
    n -= 1
    swaps = []
    for i in range(1, len(num)):
        swaps.append(int(n / (math.factorial(len(num) - i))))
        n = n % (math.factorial(len(num) - i))
    return swaps


def convert_to_answer(swaps, li):
    """
    Applies a list of switches to permutation #1.
    Param:
    swaps(list): The swaps which need to occur to turn the first permutation into the desired permutation.
    li(list): the first permutation, in list form. """
    for i in range(0, len(swaps)):
        saved = li[i]
        li[i] = li[i + swaps[i]]
        li.insert(i + 1, saved)
        del (li[i + swaps[i] + 1])
    return li


def find_permutation(string, n):
    """
    Finds the nth lexicographic permutation of a set of integers, such as 0123456789.
    Param:
    string(str): The first lexicographic permutation, given in the form of a string.
    n(integer): the permutation that you're looking for. """
    swaps = find_swaps(list(string), n)
    return convert_to_answer(swaps, list(string))


if __name__ == '__main__':
    print(find_permutation("0123456789", 1000000))
    print(find_permutation("ABCDEFG", 3))
    print(find_permutation(("tiny", "small", "medium", "large", "huge"), 12))

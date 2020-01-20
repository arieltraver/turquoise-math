def y(x):
    """A function which finds acceptable pythagorean triplets with a sum of 1000."""
    return (1_000_000 - 2000 * x) / (2000 - 2 * x)


def solve():
    """This function prints 500 possibilities, only one will be whole numbers. """
    for i in range(0, 500):
        print(f"{y(i)} {i} ")


solve()

value = 5.2342525

output = "{:.0f}".format(value)

foo = int(output)
bar = foo + 1

print(bar == 6)


def round_better(number, decimal_places):
    """A simple rounding function.
    :param number: the un-rounded number
    :param decimal_places: the desired number of post-point decimal places"""
    number *= pow(10, decimal_places)
    if decimal_places == 0:
        return int(number)
    number = int(number)
    return number / pow(10, decimal_places)


print(round_better(5.999999999999, 3))

output = "their names are {1} and {0}, but i like {0} better"
print(output.format('ariels_solution', 'austins_solution'))

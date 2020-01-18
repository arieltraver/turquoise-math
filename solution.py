def y(x):
    return (1_000_000 - 2000 * x) / (2000 - 2 * x)


def solve():
    for i in range(0, 500):
        print(f"{y(i)} {i} ")


solve()

value = 5.2342525

output = "{:.0f}".format(value)

foo = int(output)
bar = foo + 1

print(bar == 6)


def round_better(number, decimal_places):
    number *= pow(10, decimal_places)
    if decimal_places == 0:
        return int(number)
    number = int(number)
    return number / pow(10, decimal_places)


# print("{:.0f}".format(round_better(5.222, 0)))

print(round_better(5.999999999999, 0))

output = "their names are {1} and {0}, but i like {0} better"
print(output.format('ariels_solution', 'austins_solution'))

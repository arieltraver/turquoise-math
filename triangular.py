def is_triangular(i):
    k = int(pow(2 * i, 0.5))
    return k * (k + 1) == 2 * i


def factor_count(non_square):
    total = 2
    for i in range(2, int(pow(non_square, 0.5)) + 1):
        if non_square % i == 0:
            total += 2
    return total


def max_factor_triangle(low, high):
    max_factors = 2
    record_holder = low
    for i in range(low, high + 1):
        if is_triangular(i):
            n = factor_count(i)
            if n > max_factors:
                max_factors = n
                record_holder = i
    return record_holder, max_factors


print(max_factor_triangle(70_000_000, 80_000_000))

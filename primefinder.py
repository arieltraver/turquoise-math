prime_list = [2, 3, 5, 7, 11, 13]


def find_prime(n):
    import time
    start = time.time()
    prime_count = len(prime_list)
    if prime_count >= n:
        return prime_list[n - 1]
    else:
        current_number = prime_list[prime_count - 1] + 2
        while prime_count < n:
            factor_found = False
            for i in prime_list:
                if current_number % i == 0:
                    factor_found = True
                    break
                if i >= pow(current_number, .5):
                    break
            if not factor_found:
                prime_list.append(current_number)
                prime_count += 1
            current_number += 2
            factor_found = False
    elapsed = (time.time() - start)
    print(f"Found in {elapsed} seconds")
    return prime_list[n - 1]


print(find_prime(10001))
print(prime_list)

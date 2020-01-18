def is_binary_palindrome(num):
    number_string = str(num)
    number_string = number_string[2:len(number_string)]
    return number_string == number_string[len(number_string) - 1::-1]


print(is_binary_palindrome(0b101))


def produce_palindromes_10000():
    palindrome_table = []
    for first_digit in range(1, 10):
        palindrome_table.append(first_digit)

        palindrome_table.append(first_digit * 11)
        for second_digit in range(0, 10):
            palindrome_table.append(first_digit * 101 + second_digit * 10)
            palindrome_table.append(first_digit * 1001 + second_digit * 110)
    print(palindrome_table)


palindrome_table = []


def produce_palindromes_million():
    for first_digit in range(1, 10):
        palindrome_table.append(first_digit)
        palindrome_table.append(first_digit * 11)
        for second_digit in range(0, 10):
            palindrome_table.append(first_digit * 101 + second_digit * 10)
            palindrome_table.append(first_digit * 1001 + second_digit * 110)
            for third_digit in range(0, 10):
                palindrome_table.append(first_digit * 10001 + second_digit * 1010 + third_digit * 100)
                palindrome_table.append(first_digit * 100001 + second_digit * 10010 + third_digit * 1100)
    print(palindrome_table)


def check_binary_palindromes():
    total = 0
    for i in palindrome_table:
        if is_binary_palindrome(bin(i)):
            print(bin(i))
            total += i
    return total


produce_palindromes_million()
print(check_binary_palindromes())

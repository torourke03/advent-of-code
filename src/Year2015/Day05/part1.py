import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = [line.strip() for line in f.readlines()]

    vowels = "aeiou"
    bad_strings = ["ab", "cd", "pq", "xy"]
    for line in lines:
        num_vowels = 0
        has_double = False
        has_bad_string = False
        for i in range(len(line) - 1):
            if line[i] in vowels:
                num_vowels += 1
            if line[i:i + 2] in bad_strings:
                has_bad_string = True
            if line[i] == line[i + 1]:
                has_double = True
        if line[-1] in vowels:
            num_vowels += 1

        if num_vowels >= 3 and has_double and not has_bad_string:
            total += 1

    print("Total:", total)


if __name__ == "__main__":
    main()

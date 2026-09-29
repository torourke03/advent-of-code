import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = f.readlines()

    for line in lines:
        digits = line.split(" | ")[1].split()
        for digit in digits:
            if len(digit) == 2 or len(digit) == 3 or len(digit) == 4 or len(digit) == 7:
                total += 1

    print("Total:", total)


if __name__ == "__main__":
    main()

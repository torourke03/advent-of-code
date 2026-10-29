import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = [line.strip() for line in f.readlines()]

    for line in lines:
        for c in line:
            if c == "\"" or c == "\\":
                total += 1
    total += 2 * len(lines)

    print("Total:", total)


if __name__ == "__main__":
    main()

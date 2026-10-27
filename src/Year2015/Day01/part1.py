import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        line = f.readline()

    floor = 0
    for c in line:
        if c == "(":
            floor += 1
        elif c == ")":
            floor -= 1

    total = floor
    print("Total:", total)


if __name__ == "__main__":
    main()

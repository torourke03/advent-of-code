import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        line = f.readline()

    floor = 0
    for i in range(len(line)):
        if line[i] == "(":
            floor += 1
        elif line[i] == ")":
            floor -= 1
        if floor < 0:
            total = i + 1
            break

    print("Total:", total)


if __name__ == "__main__":
    main()

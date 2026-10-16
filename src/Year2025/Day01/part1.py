import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        instructions = [(line[0], int(line.strip()[1:])) for line in f.readlines()]

    pos = 50
    for instruction in instructions:
        if instruction[0] == "L":
            pos = (pos - instruction[1]) % 100
        else:
            pos = (pos + instruction[1]) % 100
        if pos == 0:
            total += 1

    print("Total:", total)


if __name__ == "__main__":
    main()

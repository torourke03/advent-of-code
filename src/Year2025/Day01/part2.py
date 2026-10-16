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
        direction = -1 if instruction[0] == "L" else 1
        for x in range(pos + direction, pos + (direction * instruction[1]) + direction, direction):
            if x % 100 == 0:
                total += 1
        pos += (instruction[1] * direction) % 100

    print("Total:", total)


if __name__ == "__main__":
    main()

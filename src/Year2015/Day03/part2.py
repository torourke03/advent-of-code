import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        line = f.readline()

    visited: set[tuple[int, int]] = set()
    x1 = 0
    y1 = 0
    x2 = 0
    y2 = 0
    visited.add((0, 0))
    for i in range(len(line)):
        c = line[i]
        if i % 2 == 0:
            if c == "^":
                y1 += 1
            elif c == "v":
                y1 -= 1
            elif c == ">":
                x1 += 1
            elif c == "<":
                x1 -= 1
            visited.add((x1, y1))
        else:
            if c == "^":
                y2 += 1
            elif c == "v":
                y2 -= 1
            elif c == ">":
                x2 += 1
            elif c == "<":
                x2 -= 1
            visited.add((x2, y2))

    total = len(visited)
    print("Total:", total)


if __name__ == "__main__":
    main()

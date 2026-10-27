import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        line = f.readline()

    visited: set[tuple[int, int]] = set()
    x = 0
    y = 0
    visited.add((x, y))
    for c in line:
        if c == "^":
            y += 1
        elif c == "v":
            y -= 1
        elif c == ">":
            x += 1
        elif c == "<":
            x -= 1
        visited.add((x, y))

    total = len(visited)
    print("Total:", total)


if __name__ == "__main__":
    main()

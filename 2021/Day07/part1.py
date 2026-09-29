import sys
from math import inf


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = inf

    with open(filename) as f:
        vals = list(map(int, f.readline().split(",")))

    for x in range(min(vals), max(vals) + 1):
        dist = sum([abs(x - val) for val in vals])
        total = min(total, dist)

    print("Total:", total)


if __name__ == "__main__":
    main()

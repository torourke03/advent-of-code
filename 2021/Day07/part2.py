import sys
from math import inf


def triangle_num(val: int) -> int:
    num = 0
    for x in range(1, val + 1):
        num += x
    return num


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = inf

    with open(filename) as f:
        vals = list(map(int, f.readline().split(",")))

    for x in range(min(vals), max(vals) + 1):
        dist = sum([triangle_num(abs(x - val)) for val in vals])
        total = min(total, dist)

    print("Total:", total)


if __name__ == "__main__":
    main()

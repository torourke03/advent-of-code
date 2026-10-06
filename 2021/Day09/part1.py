import sys
from math import inf


def check_grid(grid: list[list[int]], i: int, j: int) -> int | float:
    if 0 <= i < len(grid) and 0 <= j < len(grid[i]):
        return grid[i][j]
    else:
        return inf


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        grid = [[int(x) for x in line.strip()] for line in f.readlines()]

    for i, row in enumerate(grid):
        for j, x in enumerate(row):
            if (check_grid(grid, i - 1, j) > x and check_grid(grid, i + 1, j) > x
                    and check_grid(grid, i, j - 1) > x and check_grid(grid, i, j + 1) > x):
                total += 1 + x

    print("Total:", total)


if __name__ == "__main__":
    main()

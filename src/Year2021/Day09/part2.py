import sys
from collections import deque
from math import inf


def check_grid(grid: list[list[int]], i: int, j: int) -> int | float:
    if 0 <= i < len(grid) and 0 <= j < len(grid[i]):
        return grid[i][j]
    else:
        return inf


def basin_size(grid: list[list[int]], start: tuple[int, int]) -> int:
    adj = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    visited: set[tuple[int, int]] = {start}
    q = deque()
    q.append(start)

    while len(q) > 0:
        curr = q.popleft()
        for a in adj:
            check = tuple(map(sum, zip(a, curr)))
            if check not in visited and grid[curr[0]][curr[1]] < check_grid(grid, check[0], check[1]) < 9:
                visited.add(check)
                q.append(check)

    return len(visited)


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        grid = [[int(x) for x in line.strip()] for line in f.readlines()]

    sizes = []
    for i, row in enumerate(grid):
        for j, x in enumerate(row):
            if (check_grid(grid, i - 1, j) > x and check_grid(grid, i + 1, j) > x
                    and check_grid(grid, i, j - 1) > x and check_grid(grid, i, j + 1) > x):
                size = basin_size(grid, (i, j))
                sizes.append(size)

    sizes.sort(reverse=True)
    total = sizes[0] * sizes[1] * sizes[2]
    print("Total:", total)


if __name__ == "__main__":
    main()

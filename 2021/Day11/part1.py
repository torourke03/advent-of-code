import sys
import numpy as np

adj = np.array([[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]])


def flash(grid: np.ndarray, i: int, j: int, flashed: set[tuple[int, int]]) -> None:
    flashed.add((i, j))
    for a in adj:
        if 0 <= i + a[0] < len(grid) and 0 <= j + a[1] < len(grid[i]):
            grid[i + a[0]][j + a[1]] += 1
            if grid[i + a[0]][j + a[1]] > 9 and (i + a[0], j + a[1]) not in flashed:
                flash(grid, i + a[0], j + a[1], flashed)
    return


def step(grid: np.ndarray) -> int:
    grid += 1

    flashed: set[tuple[int, int]] = set()
    for i, j in np.ndindex(grid.shape):
        if grid[i][j] > 9 and (i, j) not in flashed:
            flash(grid, i, j, flashed)

    with np.nditer(grid, op_flags=['readwrite']) as it:
        for x in it:
            if x[...] > 9:
                x[...] = 0

    return len(flashed)


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        grid = np.array([[int(x) for x in line.strip()] for line in f.readlines()])

    for x in range(100):
        total += step(grid)

    print("Total:", total)


if __name__ == "__main__":
    main()

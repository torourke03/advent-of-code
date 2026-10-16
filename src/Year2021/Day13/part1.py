import sys


def do_fold(dots: set[tuple[int, int]], fold: tuple) -> set[tuple[int, int]]:
    folded_dots = set()
    crease = int(fold[1])
    if fold[0] == "x":
        for dot in dots:
            if dot[0] > crease:
                folded_dots.add((2 * crease - dot[0], dot[1]))
            else:
                folded_dots.add((dot[0], dot[1]))
    else:
        for dot in dots:
            if dot[1] > crease:
                folded_dots.add((dot[0], 2 * crease - dot[1]))
            else:
                folded_dots.add((dot[0], dot[1]))

    return folded_dots


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        parts = f.read().split("\n\n")
    dots = {tuple(map(int, line.split(","))) for line in parts[0].splitlines()}
    folds = [tuple(line.split()[-1].split("=")) for line in parts[1].splitlines()]

    for fold in folds[:1]:
        dots = do_fold(dots, fold)

    total = len(dots)

    print("Total:", total)


if __name__ == "__main__":
    main()

import sys


def print_dots(dots: set[tuple[int, int]]) -> None:
    max_size = (max([dot[0] for dot in dots]), max([dot[1] for dot in dots]))
    with open("output.txt", "w") as f:
        for y in range(max_size[1] + 1):
            for x in range(max_size[0] + 1):
                if (x, y) in dots:
                    f.write("#")
                else:
                    f.write(".")
            f.write("\n")

    return


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

    for fold in folds:
        dots = do_fold(dots, fold)
    print_dots(dots)

    print("Total:", total)


if __name__ == "__main__":
    main()

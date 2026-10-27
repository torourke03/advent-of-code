import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = f.readlines()

    for line in lines:
        dim = list(map(int, line.split("x")))
        dim.sort()
        total += 2 * (dim[0] + dim[1]) + dim[0] * dim[1] * dim[2]

    print("Total:", total)


if __name__ == "__main__":
    main()

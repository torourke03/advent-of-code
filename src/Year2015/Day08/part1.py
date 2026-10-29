import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = [line.strip() for line in f.readlines()]

    total = sum([len(line) for line in lines])
    for line in lines:
        count = 0
        i = 1
        while i < len(line) - 1:
            count += 1
            if line[i] == "\\":
                if line[i + 1] == "\\" or line[i + 1] == "\"":
                    i += 2
                else:  # "x"
                    i += 4
                continue
            i += 1
        total -= count

    print("Total:", total)


if __name__ == "__main__":
    main()

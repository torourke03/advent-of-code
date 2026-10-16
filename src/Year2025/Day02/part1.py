import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        ranges = [tuple(map(int, r.split("-"))) for r in f.read().split(",")]

    for r in ranges:
        for val in range(r[0], r[1] + 1):
            s = str(val)
            if s[:len(s) // 2] == s[len(s) // 2:]:
                total += val

    print("Total:", total)


if __name__ == "__main__":
    main()

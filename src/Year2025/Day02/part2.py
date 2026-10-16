import sys


def is_invalid_id(s: str) -> bool:
    for group_size in range(1, len(s) // 2 + 1):
        if len(s) % group_size != 0:
            pass
        group_str = s[:group_size]
        group_valid = True
        for i in range(group_size, len(s), group_size):
            if s[i:i + group_size] != group_str:
                group_valid = False
                break
        if group_valid:
            return True

    return False


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
            if is_invalid_id(s):
                total += val

    print("Total:", total)


if __name__ == "__main__":
    main()

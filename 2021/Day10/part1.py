import sys
from collections import deque

score_table = {")": 3, "]": 57, "}": 1197, ">": 25137}
open_chunk = "([{<"
close_chunk = ")]}>"


def calc_score(line: str) -> int:
    q = deque()

    for i in line:
        if i in open_chunk:
            q.append(i)
        else:
            c = q.pop()
            if open_chunk[close_chunk.find(i)] != c:
                return score_table[i]

    return 0


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = f.readlines()

    for line in lines:
        total += calc_score(line.strip())

    print("Total:", total)


if __name__ == "__main__":
    main()

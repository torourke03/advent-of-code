import sys
from collections import deque

score_table = {"(": 1, "[": 2, "{": 3, "<": 4}
open_chunk = "([{<"
close_chunk = ")]}>"


def get_opens(line: str) -> deque[str]:
    q = deque()

    for i in line:
        if i in open_chunk:
            q.append(i)
        else:
            c = q.pop()
            if open_chunk[close_chunk.find(i)] != c:
                return deque()

    return q


def calc_score(opens: deque[str]) -> int:
    score = 0
    while len(opens) > 0:
        s = opens.pop()
        score *= 5
        score += score_table[s]
    return score


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = f.readlines()

    scores = []
    for line in lines:
        opens = get_opens(line.strip())
        if len(opens) > 0:
            scores.append(calc_score(opens))

    total = sorted(scores)[len(scores) // 2]

    print("Total:", total)


if __name__ == "__main__":
    main()

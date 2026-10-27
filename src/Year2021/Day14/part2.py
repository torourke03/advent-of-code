import sys


def pair_insert(polymer: str, rules: dict[str, str], steps_remaining, counts: dict[str, int]) -> None:
    # Base case
    if steps_remaining == 0:
        for p in polymer[:-1]:
            counts[p] += 1
        return

    for i in range(len(polymer) - 1):
        pair_insert(polymer[i] + rules[polymer[i:i + 2]] + polymer[i + 1], rules, steps_remaining - 1, counts)
    return


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = [line.strip() for line in f.readlines()]
    polymer = lines[0]
    rules = {line.split(" -> ")[0]: line.split(" -> ")[1] for line in lines[2:]}

    counts = {x: 0 for x in {v for v in rules.values()}}
    pair_insert(polymer, rules, 40, counts)
    counts[polymer[-1]] += 1
    print(counts)

    print("Total:", total)


if __name__ == "__main__":
    main()

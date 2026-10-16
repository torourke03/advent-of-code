import sys


def pair_insert(polymer: str, rules: dict[str, str], steps_remaining) -> str:
    # Base case
    if steps_remaining == 0:
        return polymer

    new_polymer = ""
    for i in range(len(polymer) - 1):
        new_polymer += pair_insert(polymer[i] + rules[polymer[i:i + 2]] + polymer[i + 1], rules, steps_remaining - 1)[:-1]
    return new_polymer + polymer[-1]


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = [line.strip() for line in f.readlines()]
    polymer = lines[0]
    rules = {line.split(" -> ")[0]: line.split(" -> ")[1] for line in lines[2:]}

    big_polymer = pair_insert(polymer, rules, 10)
    counts = {c: big_polymer.count(c) for c in {v for v in rules.values()}}
    total = max(counts.values()) - min(counts.values())

    print("Total:", total)


if __name__ == "__main__":
    main()

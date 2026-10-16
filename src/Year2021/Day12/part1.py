import sys


# This is based on the fact that no two large caves are connected, preventing infinite loops
def count_paths(caves: dict[str, list[str]], pos: str, visited: set[str]) -> int:
    # Base cases
    if pos == "end":
        return 1
    if pos.islower() and pos in visited:
        return 0

    count = 0
    for adj in caves[pos]:
        count += count_paths(caves, adj, visited | {pos})

    return count


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    caves: dict[str, list[str]] = dict()
    with open(filename) as f:
        for line in f.readlines():
            split_line = line.strip().split("-")
            if split_line[0] not in caves.keys():
                caves[split_line[0]] = [split_line[1]]
            else:
                caves[split_line[0]].append(split_line[1])
            if split_line[1] not in caves.keys():
                caves[split_line[1]] = [split_line[0]]
            else:
                caves[split_line[1]].append(split_line[0])

    total = count_paths(caves, "start", set())

    print("Total:", total)


if __name__ == "__main__":
    main()

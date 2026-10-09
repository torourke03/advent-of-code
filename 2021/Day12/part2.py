import sys

paths = set()


# This is based on the fact that no two large caves are connected, preventing infinite loops
def count_paths(caves: dict[str, list[str]], pos: str, visited: set[str], path: list[str], double_cave: str) -> None:
    add_marker = False
    this_path = path.copy()
    this_path.append(pos)
    # Base cases
    if pos == "end":
        paths.add(",".join(this_path))
        return
    if pos.islower() and pos in visited:
        return

    if pos == double_cave and pos + "_" not in visited:
        add_marker = True

    for adj in caves[pos]:
        if add_marker:
            count_paths(caves, adj, visited | {pos + "_"}, this_path, double_cave)
        else:
            count_paths(caves, adj, visited | {pos}, this_path, double_cave)

    return


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    caves: dict[str, list[str]] = dict()
    small_caves = set()
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

            if split_line[0].islower():
                small_caves.add(split_line[0])
            if split_line[1].islower():
                small_caves.add(split_line[1])

    small_caves.remove("start")
    small_caves.remove("end")
    for small_cave in small_caves:
        count_paths(caves, "start", set(), list(), small_cave)

    total = len(paths)
    print("Total:", total)


if __name__ == "__main__":
    main()

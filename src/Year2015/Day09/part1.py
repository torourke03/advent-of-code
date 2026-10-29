import sys
from itertools import permutations


def follow_path(dists: dict[tuple[str, str], int], path: tuple[str]) -> int:
    dist = 0
    for i in range(len(path) - 1):
        dist += dists[(path[i], path[i + 1])] if (path[i], path[i + 1]) in dists.keys() else dists[(path[i + 1], path[i])]

    return dist


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = sys.maxsize

    with open(filename) as f:
        distances = [line.strip().split() for line in f.readlines()]

    dists = dict()
    cities_set = set()
    for distance in distances:
        cities_set.add(distance[0])
        cities_set.add(distance[2])
        dists[(distance[0], distance[2])] = int(distance[-1])

    for path in permutations(cities_set):
        dist = follow_path(dists, path)
        total = min(total, dist)

    print("Total:", total)


if __name__ == "__main__":
    main()

import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]

    total = 0
    fish = {x: 0 for x in range(9)}

    with open(filename) as f:
        init_vals = list(map(int, f.readline().split(",")))
    for x in init_vals:
        fish[x] += 1

    for _ in range(80):
        temp = fish[0]
        for i in range(1, 9):
            fish[i - 1] = fish[i]
        fish[8] = temp
        fish[6] += temp

    total = sum(fish.values())
    print("Total:", total)


if __name__ == "__main__":
    main()

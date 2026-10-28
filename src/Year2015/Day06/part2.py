import sys
import numpy as np


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        instructions = [line.strip().split() for line in f.readlines()]

    lights = np.full((1000, 1000), 0)
    for instruction in instructions:
        if instruction[0] == "turn":
            top_left = tuple(map(int, instruction[2].split(",")))
            bot_right = tuple(map(int, instruction[4].split(",")))
            change = 1 if instruction[1] == "on" else -1
            for x in range(top_left[0], bot_right[0] + 1):
                for y in range(top_left[1], bot_right[1] + 1):
                    lights[x][y] = max(0, lights[x][y] + change)
        else:
            top_left = tuple(map(int, instruction[1].split(",")))
            bot_right = tuple(map(int, instruction[3].split(",")))
            for x in range(top_left[0], bot_right[0] + 1):
                for y in range(top_left[1], bot_right[1] + 1):
                    lights[x][y] += 2

    for i in np.nditer(lights):
        total += i

    print("Total:", total)


if __name__ == "__main__":
    main()

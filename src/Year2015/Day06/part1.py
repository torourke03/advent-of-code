import sys
import numpy as np


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        instructions = [line.strip().split() for line in f.readlines()]

    lights = np.full((1000, 1000), False)
    for instruction in instructions:
        if instruction[0] == "turn":
            top_left = tuple(map(int, instruction[2].split(",")))
            bot_right = tuple(map(int, instruction[4].split(",")))
            turn_on = True if instruction[1] == "on" else False
            for x in range(top_left[0], bot_right[0] + 1):
                for y in range(top_left[1], bot_right[1] + 1):
                    lights[x][y] = turn_on
        else:
            top_left = tuple(map(int, instruction[1].split(",")))
            bot_right = tuple(map(int, instruction[3].split(",")))
            for x in range(top_left[0], bot_right[0] + 1):
                for y in range(top_left[1], bot_right[1] + 1):
                    lights[x][y] = not lights[x][y]

    for i in np.nditer(lights):
        if i:
            total += 1

    print("Total:", total)


if __name__ == "__main__":
    main()

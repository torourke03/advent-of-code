import sys


def get_config(numbers: list[str]) -> dict[int, str]:
    """Mainly just gotten through finding the patterns in how the number displays are set up"""
    config: dict[int, str] = dict()
    # Key = number, Value = index of numbers list
    digits: dict[int, int] = {0: -1, 1: 0, 2: -1, 3: -1, 4: 2, 5: -1, 6: -1, 7: 1, 8: 9, 9: -1}
    print(numbers)

    # Determine digit indexes
    # 3
    for x in range(3, 6):
        for i in range(len(numbers[x]) - 1):
            for j in range(i + 1, len(numbers[x])):
                if numbers[x][i] + numbers[x][j] == numbers[0]:
                    digits[3] = x

    # Top light
    for c in numbers[1]:
        if c not in numbers[0]:
            config[0] = c

    #
    print(digits)
    return config


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = [line.split(" | ") for line in f.readlines()]

    for line in lines:
        config = get_config(sorted([''.join(sorted(x)) for x in line[0].split()], key=len))
        print(config)

    print("Total:", total)


if __name__ == "__main__":
    main()

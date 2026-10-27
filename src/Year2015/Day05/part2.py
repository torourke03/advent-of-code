import sys


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        lines = [line.strip() for line in f.readlines()]

    for line in lines:
        has_aba_pattern = False
        has_matching_pair = False
        for i in range(len(line) - 2):
            if line[i] == line[i + 2]:
                has_aba_pattern = True
        for i in range(len(line) - 3):
            for j in range(i + 2, len(line) - 1):
                if line[i:i + 2] == line[j:j + 2]:
                    has_matching_pair = True
        if has_aba_pattern and has_matching_pair:
            total += 1

    print("Total:", total)


if __name__ == "__main__":
    main()

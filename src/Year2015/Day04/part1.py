import sys
import hashlib


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        secret_key = f.readline()

    while True:
        md5hash = hashlib.md5((secret_key + str(total)).encode()).hexdigest()
        if md5hash[:5] == "00000":
            break
        total += 1

    print("Total:", total)


if __name__ == "__main__":
    main()

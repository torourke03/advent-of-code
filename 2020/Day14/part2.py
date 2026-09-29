def write_mem(val: int, mask: str) -> str:
    val_str = list(format(val, "b").zfill(36))
    for i, c in enumerate(mask):
        if c == "1" or c == "X":
            val_str[i] = c
    return ''.join(val_str)


def main():
    total = 0
    with open("example.txt") as f:
        lines = f.readlines()
    
    mask = ""
    mem: list[tuple[int, str]] = list()
    for line in lines:
        split_line = line.split(" = ")
        if split_line[0] == "mask":
            mask = split_line[1].strip()
        else:
            mem.append((int(split_line[1]), write_mem(int(split_line[0][4:-1]), mask)))
    
    print(mem)
    
    print("Total:", total)
    return


if __name__ == "__main__":
    main()
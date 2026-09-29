def write_mem(val: int, mask: str) -> str:
    val_str = list(format(val, "b").zfill(36))
    for i, c in enumerate(mask):
        if c == "0" or c == "1":
            val_str[i] = c
    return ''.join(val_str)


def main():
    total = 0
    with open("input.txt") as f:
        lines = f.readlines()
    
    mask = ""
    mem: dict[int, str] = dict()
    for line in lines:
        split_line = line.split(" = ")
        if split_line[0] == "mask":
            mask = split_line[1]
        else:
            mem[int(split_line[0][4:-1])] = write_mem(int(split_line[1]), mask)
    
    total = sum(int(x, base=2) for x in mem.values())
    
    print("Total:", total)
    return


if __name__ == "__main__":
    main()
def parse_input(filename: str) -> list[int]:
    vals = []
    with open(filename) as f:
        vals = list(map(int, f.readline().split(",")))
    return vals

def main():
    total = 0
    vals = parse_input("input.txt")
    nums: dict[int, list[int]] = dict()
    
    curr_val = 0
    for count, val in enumerate(vals, start=1):
        nums[val] = [count, -1]
        curr_val = val
    
    for count in range(len(vals) + 1, 2020 + 1):
        if curr_val not in nums.keys() or nums[curr_val][1] == -1:
            curr_val = 0
            if curr_val in nums.keys():
                nums[curr_val] = [count, nums[curr_val][0]]
            else:
                nums[curr_val] = [count, -1]
        else:
            curr_val = nums[curr_val][0] - nums[curr_val][1]
            if curr_val in nums.keys():
                nums[curr_val] = [count, nums[curr_val][0]]
            else:
                nums[curr_val] = [count, -1]
    
    total = curr_val
    print("Total:", total)
    return

if __name__ == "__main__":
    main()
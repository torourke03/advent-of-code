import sys


def find_value(connections: dict[str, list[str]], wire: str, cache: dict[str, int]) -> int:
    if wire in cache.keys():
        return cache[wire]

    # Base case (and error prevention): if wire is a number, return that
    if wire.isdecimal():
        return int(wire)

    mod_val = 65535
    connection = connections[wire]
    # Base(ish) case: return value assigned to wire
    if len(connection) == 1:
        if connection[0].isdecimal():
            cache[wire] = int(connection[0])
            return int(connection[0])
        else:
            val = find_value(connections, connection[0], cache)
            cache[wire] = val
            return val

    elif connection[0] == "NOT":
        val = ~int(find_value(connections, connection[1], cache)) & mod_val
        cache[wire] = val
        return val
    elif connection[1] == "AND":
        val = find_value(connections, connection[0], cache) & find_value(connections, connection[2], cache) & mod_val
        cache[wire] = val
        return val
    elif connection[1] == "OR":
        val = find_value(connections, connection[0], cache) | find_value(connections, connection[2], cache) & mod_val
        cache[wire] = val
        return val
    elif connection[1] == "LSHIFT":
        val = find_value(connections, connection[0], cache) << find_value(connections, connection[2], cache) & mod_val
        cache[wire] = val
        return val
    else:  # RSHIFT
        val = find_value(connections, connection[0], cache) >> find_value(connections, connection[2], cache) & mod_val
        cache[wire] = val
        return val


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        connections = {line.split(" -> ")[1].strip(): line.split(" -> ")[0].split() for line in f.readlines()}

    cache = dict()
    a_value = find_value(connections, "a", cache)
    connections["b"] = [str(a_value)]
    cache.clear()
    total = find_value(connections, "a", cache)

    print("Total:", total)


if __name__ == "__main__":
    main()

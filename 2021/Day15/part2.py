import sys
import heapq


# Based on https://www.geeksforgeeks.org/dsa/dijkstras-shortest-path-algorithm-greedy-algo-7/
def dijkstra(grid: list[list[int]], start: tuple[int, int]) -> list[list[int]]:
    adjacent = [(0, -1), (0, 1), (-1, 0), (1, 0)]
    pq: list[tuple[int, tuple[int, int]]] = []
    dist = [[sys.maxsize] * len(grid) for _ in range(len(grid[0]))]

    dist[start[0]][start[1]] = 0
    heapq.heappush(pq, (0, start))

    while pq:
        d, pos = heapq.heappop(pq)

        if d > dist[pos[0]][pos[1]]:
            continue

        for adj in adjacent:
            check_pos = tuple(map(sum, zip(pos, adj)))
            if check_pos[0] < 0 or check_pos[0] >= len(grid) or check_pos[1] < 0 or check_pos[1] >= len(grid[0]):
                continue

            w = grid[check_pos[0]][check_pos[1]]

            if dist[pos[0]][pos[1]] + w < dist[check_pos[0]][check_pos[1]]:
                dist[check_pos[0]][check_pos[1]] = dist[pos[0]][pos[1]] + w
                heapq.heappush(pq, (dist[check_pos[0]][check_pos[1]], check_pos))

    return dist


def main():
    filename = "input.txt"
    if len(sys.argv) == 2:
        filename = sys.argv[1]
    total = 0

    with open(filename) as f:
        g = [[int(x) for x in line.strip()] for line in f.readlines()]
    grid: list[list[int]] = list()
    for _ in range(5):
        for row in g:
            add_row = []
            for _ in range(5):
                for val in row:
                    add_row.append(val)
            grid.append(add_row)

    for i in range(5):
        for x in range(i * (len(grid) // 5), (i + 1) * (len(grid) // 5)):
            for y in range(len(grid[x])):
                grid[x][y] = grid[x][y] + i
                if grid[x][y] > 9:
                    grid[x][y] -= 9

    for i in range(5):
        for y in range(i * (len(grid[0]) // 5), (i + 1) * (len(grid[0]) // 5)):
            for x in range(len(grid)):
                grid[x][y] = grid[x][y] + i
                if grid[x][y] > 9:
                    grid[x][y] -= 9

    dist = dijkstra(grid, (0, 0))
    total = dist[len(grid) - 1][len(grid[0]) - 1]

    print("Total:", total)


if __name__ == "__main__":
    main()

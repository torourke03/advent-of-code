#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<string> instructions;
    pair<int, int> pos = {0, 0}; // East/West = +/- x, North/South = +/- y
    pair<int, int> dir = {1, 0}; // Start east
    
    string line;
    while (getline(infile, line)) {
        instructions.push_back(line);
    }
    
    for (string instr : instructions) {
        char action = instr[0];
        int value = stoi(instr.substr(1));
        switch (action) {
        case 'N':
            pos.second += value;
            break;
        case 'S':
            pos.second -= value;
            break;
        case 'E':
            pos.first += value;
            break;
        case 'W':
            pos.first -= value;
            break;
        case 'F':
            pos.first += dir.first * value;
            pos.second += dir.second * value;
            break;
        case 'L':
            if (value == 90) {
                if (abs(dir.first) == 1) { // Facing East/West
                    dir.second = dir.first;
                    dir.first = 0;
                }
                else { // North/South
                    dir.first = -dir.second;
                    dir.second = 0;
                }
            }
            else if (value == 180) {
                dir.first *= -1;
                dir.second *= -1;
            }
            else {
                if (abs(dir.first) == 1) { // Facing East/West
                    dir.second = -dir.first;
                    dir.first = 0;
                }
                else { // North/South
                    dir.first = dir.second;
                    dir.second = 0;
                }
            }
            break;
        case 'R':
            if (value == 90) {
                if (abs(dir.first) == 1) { // Facing East/West
                    dir.second = -dir.first;
                    dir.first = 0;
                }
                else { // North/South
                    dir.first = dir.second;
                    dir.second = 0;
                }
            }
            else if (value == 180) {
                dir.first *= -1;
                dir.second *= -1;
            }
            else {
                if (abs(dir.first) == 1) { // Facing East/West
                    dir.second = dir.first;
                    dir.first = 0;
                }
                else { // North/South
                    dir.first = -dir.second;
                    dir.second = 0;
                }
            }
            break;
        }
    }
    total = abs(pos.first) + abs(pos.second);
    
    cout << "Total: " << total << endl;
    return 0;
}

#include <cmath>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

#define SIN(x) round(sin(x * 3.141592653589 / 180))
#define COS(x) round(cos(x * 3.141592653589 / 180))

// Based on https://www.geeksforgeeks.org/dsa/2d-transformation-rotation-objects/, which rotates to the left (counterclockwise)
// Modified to work with just 90-degree rotations
// x' = xcosA - ysinA
// y' = xsinA + ycosA
static pair<int, int> rotateWaypoint(pair<int, int> waypoint, int angle) {
    pair<int, int> rotatedWaypoint = {0, 0};
    //const int cosA = round(cos(angle * PI / 180));
    //const int sinA = round(sin(angle * PI / 180));
    
    rotatedWaypoint.first = (waypoint.first * COS(angle)) - (waypoint.second * SIN(angle));
    rotatedWaypoint.second = (waypoint.first * SIN(angle)) + (waypoint.second * COS(angle));
    
    return rotatedWaypoint;
}

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<string> instructions;
    pair<int, int> pos = {0, 0}; // East/West = +/- x, North/South = +/- y
    pair<int, int> waypoint = {10, 1};
    
    string line;
    while (getline(infile, line)) {
        instructions.push_back(line);
    }
    
    for (string instr : instructions) {
        char action = instr[0];
        int value = stoi(instr.substr(1));
        switch (action) {
        case 'N':
            waypoint.second += value;
            break;
        case 'S':
            waypoint.second -= value;
            break;
        case 'E':
            waypoint.first += value;
            break;
        case 'W':
            waypoint.first -= value;
            break;
        case 'F':
            pos.first += waypoint.first * value;
            pos.second += waypoint.second * value;
            break;
        case 'L':
            waypoint = rotateWaypoint(waypoint, value);
            break;
        case 'R':
            waypoint = rotateWaypoint(waypoint, 360 - value);
            break;
        }
    }
    total = abs(pos.first) + abs(pos.second);
    
    cout << "Total: " << total << endl;
    return 0;
}

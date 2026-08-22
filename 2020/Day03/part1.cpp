#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("input.txt");
    string line;
    vector<string> grid;
    pair pos = {0, 0};
    
    while (getline(infile, line)) {
        grid.push_back(line);
    }
    
    while (pos.first < grid.size() - 1) {
        pos.first++;
        pos.second = (pos.second + 3) % grid.at(0).length();
        if (grid.at(pos.first)[pos.second] == '#') total++;
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {
    long total = 1;
    ifstream infile("input.txt");
    string line;
    vector<string> grid;
    vector sleds(5, make_pair(0, 0));
    vector<pair<int, int>> slopes = {{1, 1}, {1, 3}, {1, 5}, {1, 7}, {2, 1}};
    
    while (getline(infile, line)) {
        grid.push_back(line);
    }
    
    for (int i = 0; i < sleds.size(); i++) {
        int count = 0;
        while (sleds[i].first < grid.size() - slopes[i].first) {
            sleds[i].first += slopes[i].first;
            sleds[i].second = (sleds[i].second + slopes[i].second) % grid.at(0).length();
            if (grid.at(sleds[i].first)[sleds[i].second] == '#') count++;
        }
        total *= count;
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
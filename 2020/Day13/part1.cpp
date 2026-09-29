#include <climits>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("input.txt");
    int timestamp;
    vector<int> ids;
    
    string line;
    getline(infile, line);
    timestamp = stoi(line);
    getline(infile, line);
    stringstream ss(line);
    while (getline(ss, line, ',')) {
        if (line[0] == 'x') ids.push_back(-1);
        else ids.push_back(stoi(line));
    }
    infile.close();
    
    int minVal = INT_MAX;
    for (int id : ids) {
        if (id == -1) continue;
        int dist = (((timestamp / id) + 1) * id) - timestamp;
        if (dist < minVal) {
            minVal = dist;
            total = dist * id;
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}

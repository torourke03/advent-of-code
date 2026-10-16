#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <vector>

using namespace std;

static int calcSeatId(string pass) {
    int low = 0, high = 127;
    for (int i = 0; i < 7; i++) {
        if (pass[i] == 'F') {
            high = ((high - low) / 2) + low;
        }
        else {
            low = ((high - low) / 2) + low + 1;
        }
    }
    int row = low;
    low = 0;
    high = 7;
    for (int i = 7; i < pass.size(); i++) {
        if (pass[i] == 'L') {
            high = ((high - low) / 2) + low;
        }
        else {
            low = ((high - low) / 2) + low + 1;
        }
    }
    int col = low;
    
    return (row * 8) + col;
}

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<string> passes;
    
    string line;
    map<string, string> passport;
    while (getline(infile, line)) {
        passes.push_back(line);
    }
    
    for (string pass : passes) {
        int id = calcSeatId(pass);
        if (id > total) total = id;
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
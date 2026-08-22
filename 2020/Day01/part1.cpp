#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

#define SUM_VAL 2020

int main() {
    int total = 0;
    ifstream infile("input.txt");
    string line;
    vector<int> vals;
    
    while (getline(infile, line)) {
        vals.push_back(stoi(line));
    }
    
    for (int i = 0; i < vals.size() - 1; i++) {
        for (int j = i + 1; j < vals.size(); j++) {
            if (vals[i] + vals[j] == SUM_VAL) {
                total = vals[i] * vals[j];
            }
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
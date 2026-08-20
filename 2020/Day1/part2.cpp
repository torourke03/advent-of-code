#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

#define SUM_VAL 2020

int main() {
    int total = 0;
    ifstream infile("../input.txt");
    string line;
    vector<int> vals;
    
    while (getline(infile, line)) {
        vals.push_back(stoi(line));
    }
    
    for (int i = 0; i < vals.size() - 2; i++) {
        for (int j = i + 1; j < vals.size() - 1; j++) {
            for (int k = j + 1; k < vals.size(); k++) {
                if (vals[i] + vals[j] + vals[k] == SUM_VAL) {
                    total = vals[i] * vals[j] * vals[k];
                }
            }
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
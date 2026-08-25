#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {
    const int PREAMBLE_LEN = 25;
    int total = 0;
    ifstream infile("input.txt");
    vector<long> vals;
    
    string line;
    while (getline(infile, line)) {
        vals.push_back(stol(line));
    }
    
    for (int i = PREAMBLE_LEN; i < vals.size(); i++) {
        long checkVal = vals[i];
        bool isValid = false;
        for (int j = i - PREAMBLE_LEN; j < i - 1; j++) {
            for (int k = j + 1; k < i; k++) {
                if (vals[j] + vals[k] == checkVal) {
                    isValid = true;
                }
            }
        }
        if (!isValid) {
            total = checkVal;
            break;
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
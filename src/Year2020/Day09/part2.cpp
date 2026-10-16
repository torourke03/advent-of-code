#include <climits>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {
    const int PREAMBLE_LEN = 25;
    long total = 0;
    ifstream infile("input.txt");
    vector<long> vals;
    
    string line;
    while (getline(infile, line)) {
        vals.push_back(stol(line));
    }
    
    long invalidVal = 0;
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
            invalidVal = checkVal;
            break;
        }
    }
    
    int low, high;
    bool foundRange = false;
    for (int i = 0; i < vals.size() - 1; i++) {
        long sum = vals[i];
        for (int j = i + 1; j < vals.size(); j++) {
            sum += vals[j];
            if (sum > invalidVal) break;
            if (sum == invalidVal) {
                foundRange = true;
                low = i;
                high = j;
                break;
            }
        }
        if (foundRange) {
            break;
        }
    }
    long min = LONG_MAX, max = 0;
    for (int i = low; i <= high; i++) {
        if (vals[i] > max) max = vals[i];
        if (vals[i] < min) min = vals[i];
    }
    total = min + max;
    
    cout << "Total: " << total << endl;
    return 0;
}
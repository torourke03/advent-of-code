#include <fstream>
#include <iostream>
#include <string>
#include <unordered_set>
#include <utility>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<pair<string, int>> instructions;
    unordered_set<int> visited;
    int instrPointer = 0;
    int acc = 0;
    
    string line;
    while (getline(infile, line)) {
        instructions.emplace_back(line.substr(0, 3), stoi(line.substr(4)));
    }
    
    while (visited.find(instrPointer) == visited.end()) {
        visited.insert(instrPointer);
        auto currInstr = instructions[instrPointer];
        if (currInstr.first == "nop") {
            instrPointer++;
        }
        else if (currInstr.first == "acc") {
            instrPointer++;
            acc += currInstr.second;
        }
        else { // jmp
            instrPointer += currInstr.second;
        }
    }
    total = acc;
    
    cout << "Total: " << total << endl;
    return 0;
}
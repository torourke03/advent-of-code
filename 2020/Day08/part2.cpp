#include <fstream>
#include <iostream>
#include <string>
#include <unordered_set>
#include <utility>
#include <vector>

using namespace std;

static pair<bool, int> runProgram(vector<pair<string, int>> instructions) {
    unordered_set<int> visited;
    int instrPointer = 0;
    int acc = 0;
    bool isCorrect = false;
    
    while (visited.find(instrPointer) == visited.end()) {
        if (instrPointer >= instructions.size()) {
            isCorrect = true;
            break;
        }
        
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
    
    return make_pair(isCorrect, acc);
}

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<pair<string, int>> instructions;
    
    string line;
    while (getline(infile, line)) {
        instructions.emplace_back(line.substr(0, 3), stoi(line.substr(4)));
    }
    
    for (int i = 0; i < instructions.size(); i++) {
        if (instructions[i].first == "acc") continue;
        
        pair<bool, int> result;
        if (instructions[i].first == "nop") {
            instructions[i].first = "jmp";
            result = runProgram(instructions);
            instructions[i].first = "nop";
        }
        else {
            instructions[i].first = "nop";
            result = runProgram(instructions);
            instructions[i].first = "jmp";
        }
        
        if (result.first) {
            total = result.second;
            break;
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
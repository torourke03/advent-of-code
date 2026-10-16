#include <fstream>
#include <iostream>
#include <set>
#include <string>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<vector<string>> groups;
    
    string line;
    vector<string> group;
    while (getline(infile, line)) {
        if (line.length() == 0) {
            groups.push_back(group);
            group.clear();
            continue;
        }
        group.push_back(line);
    }
    groups.push_back(group);
    group.clear();
    
    for (auto group : groups) {
        set<char> answers;
        for (string person : group) {
            for (char c : person) {
                answers.insert(c);
            }
        }
        total += answers.size();
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
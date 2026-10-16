#include <fstream>
#include <iostream>
#include <map>
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
        map<char, int> answers;
        for (string person : group) {
            set<char> singleAnswer;
            for (char c : person) {
                singleAnswer.insert(c);
            }
            for (char c : singleAnswer) {
                answers[c]++;
            }
        }
        for (auto i : answers) {
            if (i.second == group.size()) {
                total++;
            }
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
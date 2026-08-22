#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<string> passes;
    
    string line;
    map<string, string> passport;
    while (getline(infile, line)) {
        passes.push_back(line);
    }
    
    
    
    cout << "Total: " << total << endl;
    return 0;
}
#include <fstream>
#include <iostream>
#include <map>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("input.txt");
    vector<map<string, string>> passports;
    vector<string> keys = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    
    string line;
    map<string, string> passport;
    while (getline(infile, line)) {
        if (line.length() == 0) {
            passports.push_back(passport);
            passport.clear();
            continue;
        }
        stringstream ss(line);
        string token;
        while (getline(ss, token, ' ')) {
            stringstream tss(token);
            string key;
            string val;
            getline(tss, key, ':');
            getline(tss, val);
            passport[key] = val;
        }
    }
    passports.push_back(passport);
    passport.clear();
    
    for (map<string, string> p : passports) {
        bool isValid = true;
        for (string key : keys) {
            if (p.find(key) == p.end()) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            total++;
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
#include <fstream>
#include <iostream>
#include <map>
#include <regex>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

int main() {
    int total = 0;
    ifstream infile("example.txt");
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
        bool hasAllKeys = true;
        for (string key : keys) {
            if (p.find(key) == p.end()) {
                hasAllKeys = false;
                break;
            }
        }
        if (hasAllKeys) {
            map<string, string>::iterator it;
            for (it = p.begin(); it != p.end(); it++) {
                if (it->first == "byr") {
                    regex r(R"(^\d{4}$)");
                }
            }
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
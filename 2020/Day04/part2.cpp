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
        bool hasAllKeys = true;
        for (string key : keys) {
            if (p.find(key) == p.end()) {
                hasAllKeys = false;
                break;
            }
        }
        if (hasAllKeys) {
            bool allKeysValid = true;
            map<string, string>::iterator it;
            for (it = p.begin(); it != p.end(); it++) {
                if (it->first == keys[0]) { // byr - four digits; at least 1920 and at most 2002.
                    regex r(R"(^\d{4}$)");
                    if (!regex_match(it->second, r)) {
                        allKeysValid = false;
                        break;
                    }
                    int val = stoi(it->second);
                    if (val < 1920 || val > 2002) {
                        allKeysValid = false;
                        break;
                    }
                }
                else if (it->first == keys[1]) { // iyr - four digits; at least 2010 and at most 2020.
                    regex r(R"(^\d{4}$)");
                    if (!regex_match(it->second, r)) {
                        allKeysValid = false;
                        break;
                    }
                    int val = stoi(it->second);
                    if (val < 2010 || val > 2020) {
                        allKeysValid = false;
                        break;
                    }
                }
                else if (it->first == keys[2]) { // eyr - four digits; at least 2020 and at most 2030.
                    regex r(R"(^\d{4}$)");
                    if (!regex_match(it->second, r)) {
                        allKeysValid = false;
                        break;
                    }
                    int val = stoi(it->second);
                    if (val < 2020 || val > 2030) {
                        allKeysValid = false;
                        break;
                    }
                }
                /* hgt - (Height) - a number followed by either cm or in:
                 * If cm, the number must be at least 150 and at most 193.
                 * If in, the number must be at least 59 and at most 76. */
                else if (it->first == keys[3]) {
                    regex r(R"(^(\d+)(cm|in)$)");
                    smatch m;
                    if (!regex_match(it->second, m, r)) {
                        allKeysValid = false;
                        break;
                    }
                    int val = stoi(m[1].str());
                    string unit = m[2].str();
                    if ((unit == "cm" && (val < 150 || val > 193)) || (unit == "in" && (val < 59 || val > 76))) {
                        allKeysValid = false;
                        break;
                    }
                }
                else if (it->first == keys[4]) { // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                    regex r(R"(^#[0-9a-f]{6}$)");
                    if (!regex_match(it->second, r)) {
                        allKeysValid = false;
                        break;
                    }
                }
                else if (it->first == keys[5]) { // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
                    vector<string> eyeColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                    if (find(eyeColors.begin(), eyeColors.end(), it->second) == eyeColors.end()) {
                        allKeysValid = false;
                        break;
                    }
                }
                else if (it->first == keys[6]) { // pid (Passport ID) - a nine-digit number, including leading zeroes.
                    regex r(R"(^\d{9}$)");
                    if (!regex_match(it->second, r)) {
                        allKeysValid = false;
                        break;
                    }
                }
            }
            if (allKeysValid) total++;
        }
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
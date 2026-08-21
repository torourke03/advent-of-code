#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

struct Policy {
    int first{};
    int second{};
    char letter{};
    string password;
};

int main() {
    int total = 0;
    ifstream infile("input.txt");
    string line;
    vector<Policy> policies;

    while (getline(infile, line)) {
        string token;
        stringstream ss(line);
        Policy policy;

        getline(ss, token, '-');
        policy.first = stoi(token);
        getline(ss, token, ' ');
        policy.second = stoi(token);
        getline(ss, token, ' ');
        policy.letter = token[0];
        getline(ss, token);
        policy.password = token;

        policies.push_back(policy);
    }

    for (Policy policy : policies) {
        int count = 0;
        if (policy.password[policy.first - 1] == policy.letter) count++;
        if (policy.password[policy.second - 1] == policy.letter) count++;
        if (count == 1) total++;
    }
    
    cout << "Total: " << total << endl;
    return 0;
}
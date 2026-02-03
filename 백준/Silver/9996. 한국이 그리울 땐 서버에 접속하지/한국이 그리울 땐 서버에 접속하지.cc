#include<bits/stdc++.h>
using namespace std;
int n;
string p;
vector<string> split(const string & input, string delimiter) {
    vector<string> result;
    auto start = 0;
    auto end = input.find(delimiter);

    while (end != string::npos) {
        result.push_back(input.substr(start, end - start));
        start = end + delimiter.size();
        end = input.find(delimiter, start);
    }
    result.push_back(input.substr(start));
    
    return result;
}
int main() {
    cin >> n;
    cin >> p;
    vector<string> s = split(p, "*");
    string result;
    for (int i = 0 ; i < n; i++) {
        string file;
        cin >> file;
        if (file.size() < s[0].size() + s[1].size()) {
            result += "NE\n";
            continue;
        }
        // 두가지 방법이 있는거 아닌가?
        // "*"로 스플릿하고 file이 앞뒤로 일치하는지 확인 
        // cout << file.substr(0, s[0].size()) << " : " << file.substr(file.size() - s[1].size()) << "\n";
        if (file.substr(0, s[0].size()) == s[0] && file.substr(file.size() - s[1].size()) == s[1]) {
            result+="DA\n";
        } else {
            result+="NE\n";
        }
    }

    cout << result << "\n";

    return 0;
}
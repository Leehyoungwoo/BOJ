#include <bits/stdc++.h>
using namespace std;
int t;
string p;
int n;
string arr;
string ret;
deque<int> dq;
int pc;
// r은 뒤집기, d는 버리기
vector<string> split(const string &s, string delimiter) {
    vector<string> ret;
    int start = 0;
    int end = s.find(delimiter);
    while (end != string::npos) {
        ret.push_back(s.substr(start, end - start));
        start = end + (int) delimiter.size();
        end = s.find(delimiter, start);
    }
    ret.push_back(s.substr(start));
    return ret;
}
int main() {
    cin >> t;
    for (int i = 0; i < t; i++){
        dq.clear();
        pc = 0;
        cin >> p;
        cin >> n;
        cin >> arr;
        arr = arr.substr(1, arr.size() - 2);
        auto v = split(arr, ",");
        if (n > 0) {
            for (int j = 0; j < n; j++) {
                dq.push_back(stoi(v[j]));
            }
        }

        bool err = false;
        for (int j = 0; j < (int)p.size(); j++) {
            char c = p[j];
            if (c == 'R') {
                pc++;
            } else {
                if (dq.empty()) {
                    err = true;
                    break;
                }
                if (pc % 2 == 0) {
                    dq.pop_front();
                } else {
                    dq.pop_back();
                }
            }
        }
        if (err) {
            ret+="error\n";
            continue;
        }
        string temp = "[";
        while (dq.size()) {
            int cur = 0;
            if (pc % 2 == 0) {
                cur = dq.front(); dq.pop_front();
            } else {
                cur = dq.back(); dq.pop_back();
            }
            temp+=to_string(cur);
            if (!dq.size()) {
                break;
            }
            temp+=",";
        }
        temp+=']';
        ret+=temp + '\n';
    }

    cout << ret << '\n';
    return 0;
}
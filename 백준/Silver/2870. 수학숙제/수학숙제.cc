#include <bits/stdc++.h>
using namespace std;
int n;
vector<string> v;
string erase_zero(const string& a){
    int idx = 0;
    while (idx < (int)a.size() && a[idx] == '0') idx++;
    if (idx == (int)a.size()) return "0";
    return a.substr(idx);
}

bool cmp(const string & a, const string & b) {
    if (a.length() != b.length()) {
        return a.length() < b.length();
    } else {
        int idx = 0;
        while (idx < a.length() && a[idx] == b[idx]) {
            idx++;
        }

        return a[idx] < b[idx];
    }
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        // 문자열탐색하면서 숫자만 저장할거
        string s;
        cin >> s;

        int l = 0;
        int r = l;
        while (r < s.length()) {
            if (isdigit(s[r])) {
                r++;
            } else{
                if (l == r) {
                    l++;
                    r = l;
                } else {
                    // l ~ r - 1까지 
                    string sub = s.substr(l, r - l);
                    // 숫자를 저장할 떄, 앞에 있는 0들은 제거(숫자 0은 들어감)
                    v.push_back(erase_zero(sub));
                    l = r + 1;
                    r = l;
                }
            }
        }
        if (l < r) v.push_back(erase_zero(s.substr(l, r - l)));
        // 숫자를 저장할 떄, 앞에 있는 0들은 제거(숫자 0은 들어감)
    }
    sort(v.begin(), v.end(), cmp);
    for(string i : v) cout << i << "\n";
    return 0;
}
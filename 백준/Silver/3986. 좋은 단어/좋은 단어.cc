#include<bits/stdc++.h>
using namespace std;
int n;
vector<string> v;
int main() {
    cin >> n;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        stack<char> st;
        for (int i = 0; i < s.size(); i++) {
            if (!st.empty() && st.top() == s[i]) st.pop();
            else st.push(s[i]);
        } 
        if (st.empty()) {
            cnt++;
        }
    }
    cout << cnt << "\n";

    return 0;
}
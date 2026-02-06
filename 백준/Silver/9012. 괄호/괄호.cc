#include <bits/stdc++.h>
using namespace std;
int n;
string s;
string ret;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> s;
        stack<char> st;
        for (int j = 0; j < s.length(); j++) {
            char c = s[j];
            if (c == ')' && !st.empty() && st.top() == '(') {
                st.pop();
                continue;
            }

            st.push(c);
        }

        if (st.empty()) {
            ret+="YES";
        } else {
            ret+="NO";
        }
        ret+="\n";
    }

    cout << ret << "\n";

    return 0;
}
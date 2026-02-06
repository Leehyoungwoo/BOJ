#include <bits/stdc++.h>
using namespace std;
string s;
string ret;
bool valid(char c) {
    return c == '(' || c == ')' || c == '[' || c == ']';
}
int main(){
    while (true) {
        getline(cin, s);
        if (s == ".") break;
        stack<char> st;
        for (int i = 0; i < s.length(); i++) {
            char c = s[i];
            if (!valid(c)) {
                continue;
            }

            if (!st.empty() && st.top() == '(' && c == ')') {
                st.pop();
                continue;
            }

            if (!st.empty() && st.top() == '[' && c == ']') {
                st.pop();
                continue;
            }

            st.push(c);
        }
        if (st.empty()) ret+="yes";
        else ret+="no";

        ret+="\n";
    }

    cout << ret << "\n";

    return 0;
}
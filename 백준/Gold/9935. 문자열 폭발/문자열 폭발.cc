#include <bits/stdc++.h>
using namespace std;
string s;
string bomb;
stack<char> st;
string ret;
void go() {
    string temp;
    int cnt = 0;
    for (int i = (int)bomb.size() - 1; i >= 0; i--) {
        if (bomb[i] == st.top()) {
            cnt++;
            temp+=st.top();
            st.pop();
        }
    }

    if (cnt == (int) bomb.size()) {
        return;
    } else {
        // 다시 거꾸로 넣어주자
        for (int i = (int)temp.size() - 1; i >= 0; i--) {
            st.push(temp[i]);
        }
    }
}
int main(){
    cin >> s >> bomb;
    if ((int)s.size() < (int)bomb.size()) {
        cout << s << '\n';
        return 0 ;
    }
    
    for (int i = 0; i < (int) s.size(); i++) {
        char c = s[i];
        st.push(c);

        if (st.size() && st.top() == bomb[bomb.size() - 1] && (int)st.size() >= (int)bomb.size()) {
            go();
        }
    }

    if(st.empty()) {
        cout << "FRULA" << '\n';
        return 0;
    }

    while (st.size()) {
        ret += st.top();
        st.pop();
    }
    
    reverse(ret.begin(), ret.end());

    cout << ret << '\n';
    return 0;
}
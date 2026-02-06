#include <bits/stdc++.h>
using namespace std;
string s;
string ret;
set<char> p = {'a', 'e', 'i', 'o', 'u'};
bool go1(string &s) {
    // 1. 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
    for (char c : s) {
        if (p.count(c)) {
            return true;
        }
    }

    return false;
}
bool go2(string &s) {
    if (s.length() < 3) return true;
    // 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
    for(int i = 0; i < s.length() - 2; i++) {
        string su = s.substr(i, 3);
        int cnt = 0;
        for (int j = 0; j < su.length(); j++) {
            if (p.count(su[j])) {
                cnt++;
            }
        }
        if (cnt == 0 || cnt == 3) {
            return false;
        }
    }

    return true;
}
bool go3(string &s) {
    // 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
    for (int i = 0; i < s.length() - 1; i++) {
        string t = s.substr(i, 2);
        if (t[0] == t[1]) {
            if ((t[0] != 'e' && t[0] != 'o')) {
                return false;
            } 
        }
    }

    return true;
}
int main(){
    while (true) {
        cin >> s;
        if (s == "end") {
            break;
        }
        string p = "<" + s + ">"; 
        ret+=p;
        if (go1(s) && go2(s) && go3(s)) {
            ret += " is acceptable.";
        } else {
            ret+= " is not acceptable.";
        }
        ret+="\n";
    }

    cout << ret << "\n";

    return 0;
}
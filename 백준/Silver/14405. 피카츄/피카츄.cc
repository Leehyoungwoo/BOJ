#include <bits/stdc++.h>
using namespace std;
string s;
bool found;
const vector<string> v = {"pi", "ka", "chu"};
void check(int start, const string & s) {
    if (found) return;
    if (start == (int)s.size()) {
        found = true;
        return;
    }
    if (start + 2 <=(int) s.size()) {
        string sub = s.substr(start, 2);
        if (sub == "pi" || sub == "ka") {
            check(start + 2, s);
        }
    } 
    if (start + 3 <= (int)s.size()) {
        string sub = s.substr(start, 3);
        if (sub == "chu") {
            check(start + 3, s);
        }
    } 
}
int main() {
    cin >> s;
    check(0, s);
    if (found){
        cout << "YES" << '\n';
    } else {
        cout << "NO" << '\n';
    }
    return 0;
}
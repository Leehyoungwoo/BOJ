#include <bits/stdc++.h>
using namespace std;
int m;
// 숫자는 1~20
// add는 x추가 있으면 무시
// remove x s에서 x제거 없으면 무시
// check x 있으면 1, 없으면 x
// toggle s에 x가 있으면 제거 없으면 x추가
// all -> S= 1,2,3...20
// empty -> 공집합
int S;
int main(){
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> m;
    for (int i = 0; i < m; i++){
        string s; int x;
        cin >> s;
        if (s != "all" && s != "empty") {
            cin >> x;
        }
        
        if (s == "add") {
            S |= (1 << x);
        } else if (s == "remove") {
            S&=~(1 << x);
        } else if (s == "check") {
            if (S & (1<<x)) {
                cout << '1' << '\n';
            } else {
                cout << '0' << '\n';
            }
        } else if (s == "toggle") {
            S^=(1<<x);
        } else if (s == "all") {
            S = (1 << 21) - 1;
        } else {
            // empty 
            S = 0;
        }

    }

    return 0;
}
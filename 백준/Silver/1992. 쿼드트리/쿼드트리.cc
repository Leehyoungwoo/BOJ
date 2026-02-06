#include <bits/stdc++.h>
using namespace std;
// 분할정복인거 같은데 -> 모두 0이면 0, 모두 1이면 1, 완쪽위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순으로 표현
int n;
int mp[66][66];

string quad(int sr, int sc, int size) {
    if (size == 1) {
        return to_string(mp[sr][sc]);
    }

    int t = mp[sr][sc];
    string ret = "";
    bool flag = 0;
    for (int i = sr; i < sr + size; i++) {
        for (int j = sc; j < sc + size; j++){
            if (t != mp[i][j]) {
                ret+='(';
                ret+= quad(sr, sc, size / 2);
                ret+= quad(sr, sc + size / 2, size / 2);
                ret += quad(sr + size / 2, sc, size / 2);
                ret += quad(sr + size / 2, sc + size / 2, size / 2);
                ret +=")";
                return ret;
            }
        }
    }

    return to_string(mp[sr][sc]);
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < n; j++) {
            mp[i][j] = s[j] - '0';
        }
    }

    cout << quad(0, 0, n) << "\n";

    return 0;
}
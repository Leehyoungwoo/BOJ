#include <bits/stdc++.h>
using namespace std;
int t, k;
int a[1001][8];
// n은 0극, s는 1
vector<pair<int, int>> v;
int cnt;
int no, d;
int dir[1001];
void turn(int d, int idx) {
    if(d == 0) return;
    if (d == 1) {
        int prev = a[idx][0];
        for (int i = 1; i < 8; i++) {
            int temp = a[idx][i];
            a[idx][i] = prev;
            prev = temp;
        }
        a[idx][0] = prev;
    } else {
        int prev = a[idx][7];
        for (int i = 6; i >= 0; i--) {
            int temp = a[idx][i];
            a[idx][i] = prev;
            prev = temp;
        }
        a[idx][7] = prev;
    }
};
// 내기준 2, 상대 기준 6, 내기준 6, 상대기준 2
void goleft(int from ,int idx, int move, int d) {
    dir[idx] = d;
    if (idx + move < 0 || a[idx][6] == a[idx + move][2]) return;
    goleft(from, idx + move, move, -d);
}
void goright(int from, int idx, int move, int d) {
    dir[idx] = d;
    if (idx + move >= t || a[idx][2] == a[idx + move][6]) return;
    goright(from, idx + move, move, -d);   
}
int main(){
    cin >> t;
    fill(&a[0][0], &a[0][0] + 1001 * 8, -1);
    for (int i = 0; i < t; i++) {
        string s; 
        cin >> s;
        for (int j = 0; j < 8; j++) {
            a[i][j] = s[j] - '0';
        }
    }
    cin >> k;
    for (int i = 0; i < k; i++) {
        int idx, dir;
        cin >> idx >> dir;
        v.push_back({idx - 1, dir});
    }
    for (int i = 0; i < (int) v.size(); i++) {
        fill(dir, dir + t, 0);
        tie(no, d) = v[i];
        goleft(no, no, -1, d);
        goright(no, no, 1, d);

        for (int j = 0; j < t; j++) {
            turn(dir[j], j);
        }
    }
    for (int i = 0; i < t; i++) {
        if (a[i][0] == 1) {
            cnt++;
        }
    }
    cout << cnt << '\n';
    return 0;
}
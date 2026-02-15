#include <bits/stdc++.h>
using namespace std;
int n;
int a[21][21];
vector<int> s;
vector<int> l;
int mn = INT_MAX;
int score(vector<int> v) {
    int sz = (int)v.size();
    int sum = 0;
    for (int i = 0; i < sz; i++) {
        for (int j = i + 1; j < sz; j++) {
            sum += a[v[i]][v[j]];
            sum += a[v[j]][v[i]];
        }
    }

    return sum;
}
bool valid(int mask) {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (mask & (1 << i)) {
            cnt++;
        }
    }
    return cnt == n / 2;
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }
    for (int k = 0; k < (1 << n); k++) {
        s.clear();
        l.clear();
        // xor비트 2^n개
        int mask = k;
        // 만약에 절반씩이 아니면 fasle
        if (!valid(mask)) continue;
        // 팀별 능력치 계산. 스타크 팀 0, 링크 팀 1
        for (int i = 0; i < n; i++) {
            if (mask & (1 << i)) {
                s.push_back(i);
            } else {
                l.push_back(i);
            }
        }
        int diff = abs(score(s) - score(l));
        mn = min(mn, diff);
    }

    cout << mn << '\n';

    return 0;
}
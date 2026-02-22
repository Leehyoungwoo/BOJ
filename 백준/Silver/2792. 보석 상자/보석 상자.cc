#include <bits/stdc++.h>
using namespace std;
int n, m;
int color[300001];
// 한 아이가 너무 많이 가져가면 그게 질투심이 됨
// 가장 많이 가져간게 질투심
// 각 컬러별로 주어지고
// 학생은 항상 같은 색상의 보석만
// 모든 보석을 나눠줘야함
bool canDivide(int target) {
    // 애가 n명이고 
    // 나눠줄 값이잖아
    int cnt = 0;
    for (int i = 0; i < m; i++) {
        cnt+=(color[i] / target);
        if (color[i] % target != 0) {
            cnt++;
        }
    }
    return cnt <= n;
}
int main() {
    int l = 1;
    int r = 0;
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        cin >> color[i];
        r = max(r, color[i]);
    }
    
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (!canDivide(mid)) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    cout << l << '\n';
    return 0;
}
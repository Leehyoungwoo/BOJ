#include <bits/stdc++.h>
using namespace std;
int n;
vector<pair<int ,int>> v;
int cnt;
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        int start, end;
        cin >> start >> end;
        v.push_back({end, start});
    }
    cnt = 1;
    sort(v.begin(), v.end());
    int lastEnd = v[0].first;
    for (int i = 1; i < n; i++) {
        auto it = v[i];
        // 시작이 마지막 끝보다 앞이면 불가
        if (it.second < lastEnd) {
            continue;
        } 
        cnt++;
        lastEnd = it.first;
    }

    cout << cnt << '\n';
    return 0;
}
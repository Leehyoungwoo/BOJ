#include <bits/stdc++.h>
using namespace std;
int n;
int a[1001];
int cnt[1001];
int prev_list[1001];
int ret;
int idx;
vector<int> v;
void go(int idx) {
    if (idx == -1) return;
    v.push_back(a[idx]);
    go(prev_list[idx]);
} 
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    fill(cnt, cnt + 1001, 1);
    fill(prev_list, prev_list + 1001, -1);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (a[j] < a[i] && cnt[i] < cnt[j] + 1) {
                cnt[i] = cnt[j] + 1;
                prev_list[i] = j;
            }
        }
        if (ret < cnt[i]) {
            ret = cnt[i];
            idx = i;
        }
    }

    cout << ret << '\n';
    go(idx);
    for (int i = v.size() - 1; i >= 0; i--) cout << v[i] << ' ';
    return 0;
}
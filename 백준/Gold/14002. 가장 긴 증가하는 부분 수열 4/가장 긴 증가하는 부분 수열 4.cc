#include <bits/stdc++.h>
using namespace std;
int n;
int idx;
int ret = 1;
int cnt[1001];
int prev_list[1001];
int a[1001];
vector<int> v;
void go(int idx) {
    if (idx == -1) {
        return;
    }
    v.push_back(a[idx]);
    go(prev_list[idx]);
    return; 
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    fill(prev_list, prev_list + 1001, -1);
    fill(cnt, cnt + 1001, 1);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if(a[j] < a[i] && cnt[i] < cnt[j] + 1){
                cnt[i] = cnt[j] + 1;
                prev_list[i] = j;
                if (ret < cnt[i]) {
                    ret = cnt[i];
                    idx = i;
                }
            }
        }
    }
    cout << ret << '\n';
    go(idx);
    string ar;
    for (int i = v.size() - 1; i >= 0; i--) {
        ar += to_string(v[i]) + " ";
    }
    cout << ar << '\n';
    return 0;
}
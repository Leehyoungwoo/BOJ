#include <bits/stdc++.h>
using namespace std;
int n;
vector<pair<int, int>> v;
int last_day;
priority_queue<int> pq;
int ret;
bool cmp(const pair<int, int> &a, const pair<int, int> &b) {
    return a.second > b.second;
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++){
        int d, p;
        cin >> p >> d;
        v.push_back({p, d});
        last_day = max(last_day, d);
    }

    // 20일이라 치면 20일보다 크거나 같은 거만 20일에 배치될 수 있음 -> 그중에 제일 비싼거
    sort(v.begin(), v.end(), cmp);
    int idx = 0;
    for (int day = last_day; day > 0; day--) {
        while (idx < n && v[idx].second >= day) {
            pq.push(v[idx].first);
            idx++;
        }

        if (!pq.empty()) {
            ret+=pq.top();
            pq.pop();
        }
    }

    cout << ret << '\n';
    return 0;
}
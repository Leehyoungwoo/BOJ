#include <bits/stdc++.h>
using namespace std;
int last_day;
int n;
vector<pair<int, int>> v;
int ret;
priority_queue<int> pq;
bool cmp(const pair<int, int> &a, const pair<int, int> &b) {
    return a.first > b.first;
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++){
        int d, c;
        cin >> d >> c;
        v.push_back({d, c});
        last_day = max(last_day, d);
    }
    sort(v.begin(), v.end(), cmp);
    int idx = 0;
    // 뒤에서부터 하는 이유 -> 데드라인이 날짜보다 크거나 같아야 가능, 데드라인이 큰 것에서 작은것을 담기 떄문에 담겨있던 것들은 계속 해당 날짜에 문제 풀이 가능
    for (int day = last_day; day > 0; day--){
        while (idx < n && v[idx].first >= day) {
            pq.push(v[idx].second);
            idx++;
        }
        // 해당 날짜에 풀 문제
        if (!pq.empty()) {
            ret+=pq.top();
            pq.pop();
        }
    }
    cout << ret << '\n';
    return 0;
}
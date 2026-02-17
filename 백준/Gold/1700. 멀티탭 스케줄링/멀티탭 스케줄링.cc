#include <bits/stdc++.h>
using namespace std;
int n, k;
int a[101];
int ret;
map<int, int> curNext;
map<int, queue<int>> nxt;
set<int> visited;
struct cmp{
    bool operator()(const pair<int, int> &a, const pair<int, int> &b) const{
        // 다음 인덱스
        return a.first < b.first;
    }
};
priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> pq;
int main() {
    cin >> n >> k;
    for (int i = 0; i < k; i++) {
        cin >> a[i];
    }
    // next 맵에 다음에 등장하는 모든 인덱스를 넣자 
    for (int i = 0; i < k; i++) {
        int num = a[i];
        nxt[num].push(i);
    }
    // 배열 순회 -> pq.에 집어넣어 -> pq.size() >= k면 다음 인덱스가 가장 큰거 하나 빼고 넣어 -> 인덱스, 제품이렇게 넣어
    for (int i = 0; i < k; i++) {
        int num = a[i];
        // 현재 인덱스 -> 다음을 저장해야함
        nxt[num].pop();
        int nIdx = nxt[num].empty() ? INT_MAX : nxt[num].front();
        curNext[num] = nIdx;
        if (visited.count(num)) {
            pq.push({nIdx, num});
            continue;
        }

        if ((int) visited.size() == n) {
            while (true) {
                auto [idx, x] = pq.top();
                pq.pop();
                if (visited.count(x) && curNext[x] == idx) {
                    visited.erase(x);
                    ret++;
                    break;
                } 
            }
        }

        visited.insert(num);
        pq.push({nIdx, num});
    }

    cout << ret << '\n';
    return 0;
}
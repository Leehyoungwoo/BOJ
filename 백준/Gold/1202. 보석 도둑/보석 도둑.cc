#include <bits/stdc++.h>
using namespace std;
int n, k;
vector<pair<int, int>> jew;
vector<int> bag;
long long sum;
// 가방 작은 것도 다 채워야하니까
// 가방 작은 순으로 순회 돌고 가능한 가격을 pq에 담아두면서 하나씩 담기
int main(){
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        int w, p;
        cin >> w >> p;
        jew.push_back({w, p});
    }
    for (int i = 0; i < k; i++) {
        int b;
        cin >> b; 
        bag.push_back(b);
    }
    sort(jew.begin(), jew.end());
    sort(bag.begin(), bag.end());
    int idx = 0;
    priority_queue<int> j;
    for (int cap : bag) {
        // 가방에 담을 수 있는 모든 후보군을 담을거니까 보석을 무게 순 오름 차순 정렬
        while (idx < n && jew[idx].first <= cap) {
            // 후보군을 내림차순으로 저장하면서 인덱스 증가
            j.push(jew[idx].second);
            idx++;
        }
        // 비어있지 않으면 하나는 넣을 수 있음
        if (!j.empty()) {
            // 가장 앞에 제일 큰 가격의 보석 더하기
            sum+=j.top();
            j.pop();
        }
    }
    cout << sum << '\n';
    return 0;
}
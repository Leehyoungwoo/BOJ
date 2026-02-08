#include <bits/stdc++.h>
using namespace std;
int n, k;
// 최소 거리를 더해주는건데 이걸로 거리 체크만 해야지 방문 카운터 체크를 마지막에만 하면 안됨 -> 예를 들어서 4 10 -> 5 -> 10이 최단 거리일텐데 5까지 몇가지인지 알아야 함 
int visited[100003];
int way[100003];
bool in (int cur) {
    return 0 <= cur && cur <= 100000;
}
int main() {
    fill(visited, visited + 100004, 100003);
    cin >> n >> k;
    queue<int> q;
    q.push(n);
    visited[n] = 1;
    way[n] = 1;
    while (q.size()) {
        int cur = q.front();
        q.pop();
        int nxt[3] = {cur + 1, cur - 1, cur * 2};
        for (int next : nxt) {
            if (!in(next)) continue;
            // 이전 거리보다 짧아야 의미있는거 아닌가
            // 최단거리 갱신되면 최단거리 가짓수도 갱신
            if (visited[cur] + 1 < visited[next]) {
                visited[next] = visited[cur] + 1;
                // 이렇게 오는 가짓수는 cur까지 오는 가짓수
                way[next] = way[cur];
                q.push(next);
            } else if (visited[cur] + 1 == visited[next]) {
                // 이렇게 오는 가짓수는 cur까지 오는 가짓수 더하자
                // next 이미 방문했다는 얘기 아닌가?
                way[next]+=way[cur];
            }
        }
    }

    cout << visited[k] - 1 << "\n";
    cout << way[k] <<"\n";
    return 0;
}
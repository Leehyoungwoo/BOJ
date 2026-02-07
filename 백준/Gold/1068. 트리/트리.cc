#include <bits/stdc++.h>
using namespace std;
int n;
vector<int> adj[51];
bool visited[51];
int r;
int root;
int dfs(int cur) {
    // 제거 노드
    if (cur == r) {
        // cout << "제거노드 " << cur << " 입니다"<<"\n";
        return 0;
    }

    // cout << "재귀 : " << cur << " 입니다";
    visited[cur] = true;
    int cnt = 0;
    for (int next : adj[cur]) {
        if (!visited[next] && next != r) {
            // cout << next << "\n";
            cnt+=dfs(next);
        }
    }
    return cnt == 0 ? 1 : cnt;
}
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        int node;
        cin >> node;
        if (node == -1) {
            // 루트임
            // 혹은 부모가 없음
            root = i;
            continue;
        }
        // i의 부모가 node;
        adj[node].push_back(i);
    }
    cin >> r;

    cout << dfs(root) << "\n";

    return 0;
}
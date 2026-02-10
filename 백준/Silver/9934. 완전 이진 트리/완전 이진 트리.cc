#include <bits/stdc++.h>
using namespace std;
int k;
int a[1024];
// 중위 순회의 결과물이 배열에 들어있음
// 그러면 배열 가운데가 미드 => root
// root 왼쪽은 왼쪽 서브트리, 오른쪽은 오른쪽 서브트리
// 인접 리스트 만들어서 레벨별 bfs, dfs하면 결과 나올듯?
// 인자로 부모, 트리의 왼쪽 범위, 오른쪽 범위로 넘기면 되지 않을까?
int root;
vector<vector<int>> v;
void dfs(int mid, int left, int right, int depth) {
    if (depth == k) {
        return;
    }
    int leftMid = (left + mid - 1) / 2;
    int rightMid = (mid + 1 + right) / 2;
    v[mid].push_back(leftMid);
    v[mid].push_back(rightMid);
    dfs(leftMid, left, mid - 1, depth + 1);
    dfs(rightMid, mid + 1, right, depth + 1);
}
int main(){
    cin >> k;
    for (int i = 0; i < (int)pow(2, k) - 1; i++) {
        cin >> a[i];
    }
    int sz = (int)pow(2, k) - 1;
    v.resize(sz);
    // cout << sz << "\n";
    root = sz / 2;
    // cout << "루트 : " << root << '\n';
    dfs(root, 0, sz - 1, 1);

    queue<int> q;
    q.push(root);

    while (q.size()) {
        int lv = q.size();
        for (int i = 0; i < lv; i++) {
            int cur = q.front(); q.pop();
            cout << a[cur] << " ";
            for (auto next : v[cur]) {
                q.push(next);
            }
        }
        cout << '\n';
    }
    return 0;
}
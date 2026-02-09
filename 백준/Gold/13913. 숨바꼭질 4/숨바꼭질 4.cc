#include <bits/stdc++.h>
using namespace std;
int n, k;
const int INF = 1000004;
int visited[INF];
map<int, int> parent;
vector<int> p;
string ret;
int main(){
    cin >> n >> k;
    if (n == k) {
        cout << 0 << "\n";
        cout << n << "\n";
        return 0;
    }
    queue<int> q;
    q.push(n);
    visited[n] = 1;
    while (q.size()) {
        int cur = q.front();
        q.pop();
        if (cur == k) {
            break;
        }
        for (int next : {cur + 1, cur - 1, cur * 2}) {
            if (0 <= next && next < INF && !visited[next]) {
                visited[next] = visited[cur] + 1;
                q.push(next);
                parent[next] = cur;
            }
        }
    }
    int cur = k;
    
    while (cur != n) {
        p.push_back(cur);
        cur = parent[cur];
    }
    p.push_back(n);
    reverse(p.begin(), p.end());

    for (int i : p) ret += to_string(i) + " ";

    cout << visited[k] - 1<< "\n";
    cout << ret << "\n";

    return 0;
}
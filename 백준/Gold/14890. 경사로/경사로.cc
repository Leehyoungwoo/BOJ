#include <bits/stdc++.h>
using namespace std; 
int n, l;
int mp[101][101];
bool visited[101];
int ret;
bool valid_row_way(int r) {
    memset(visited, false, sizeof(visited));
    for (int i = 0; i < n - 1; i++) {
        int cur = mp[r][i];
        int nxt = mp[r][i + 1];
        if (cur == nxt) continue;

        int diff = nxt - cur;
        if (abs(diff) != 1) return false;

        if (diff == 1) {
            for (int k = i; k > i - l; k--) {
                if (k < 0) return false;
                if (visited[k]) return false;
                if (mp[r][k] != cur) return false;
                visited[k] = true;
            }
        } else { 
            for (int k = i + 1; k < i + 1 + l; k++) {
                if (k >= n) return false;
                if (visited[k]) return false;
                if (mp[r][k] != nxt) return false;
                visited[k] = true;
            }
        }
    }
    return true;
}

bool valid_col_way(int c) {
    memset(visited, false, sizeof(visited));
    for (int i = 0; i < n - 1; i++) {
        int cur = mp[i][c];
        int nxt = mp[i + 1][c];
        if (cur == nxt) continue;

        int diff = nxt - cur;
        if (abs(diff) != 1) return false;

        if (diff == 1) { 
            for (int k = i; k > i - l; k--) {
                if (k < 0) return false;
                if (visited[k]) return false;
                if (mp[k][c] != cur) return false;
                visited[k] = true;
            }
        } else { 
            for (int k = i + 1; k < i + 1 + l; k++) {
                if (k >= n) return false;
                if (visited[k]) return false;
                if (mp[k][c] != nxt) return false;
                visited[k] = true;
            }
        }
    }
    return true;
}
int main(){
    cin >> n >> l;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> mp[i][j];
        }
    }
    
    for(int i = 0; i < n; i++) {
        if (valid_row_way(i)) {
            ret++;
        }
        if(valid_col_way(i)) {
            ret++;
        }
    }

    cout << ret << '\n';
    return 0;
}
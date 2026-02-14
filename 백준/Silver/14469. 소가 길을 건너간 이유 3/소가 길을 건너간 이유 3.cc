#include <bits/stdc++.h>
using namespace std;
int n;
vector<pair<int, int>> v;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++){
        int a, b;
        cin >> a >> b;
        v.push_back({a, b});
    }

    sort(v.begin(), v.end());
    int time = 0;
    for (int i = 0; i < n; i++) {
        auto it = v[i];
        time = max(time, it.first);
        time += it.second;
    }
    cout << time << '\n';
    return 0;
}
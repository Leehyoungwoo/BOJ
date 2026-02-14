#include <bits/stdc++.h>
using namespace std;
int n;
int sum = 0;
vector<pair<int, int>> v;
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        v.push_back({x, y});
    }
    sort(v.begin(), v.end());
    int start = v[0].first;
    int end = v[0].second;

    for (int i = 1; i < n; i++) {
        auto it = v[i];
        // start <= 다음선의 first가 <=end 
        // end 늘려줌
        if (it.first <= end) {
            end = max(end, it.second);
            continue;
        }
        //
        if (end < it.first) {
            sum += end - start;
            start = it.first;
            end = it.second;
        }   
    }
    
    sum+=end-start;

    cout << sum << '\n';
    return 0;
}
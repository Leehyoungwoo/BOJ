#include <bits/stdc++.h>
using namespace std;
int n, l;
int ret;
vector<pair<int, int>> v;
// 물웅덩이 시작점을 보고 다 덮힐때까지 ret++; 어디까지 덮혔는지를 체크
int main(){
    cin >> n >> l;
    for (int i = 0; i < n; i++) {
        int s, e;
        cin >> s >> e;
        v.push_back({s, e});
    }
    
    sort(v.begin(), v.end());
    int cur = 0;
    for (int i = 0; i < (int) v.size(); i++) {
        int s, e; 
        tie(s, e) = v[i];
        int start = max(cur, s);
        while (start < e) {
            start+=l;
            ret++;
        }
        cur = start;
    }
    cout << ret << '\n';
    return 0;
}
#include <bits/stdc++.h>
using namespace std;
// 빈도 조사
// 빈도를 세면서 많이 등장하는 경우가 앞으로 가게, 같다면 먼저 나온 것이 앞에
// 우선 순위큐 혹은 정렬 기준
int n, c;
string ret;
bool cmp(const tuple<int, int, int>& a, const tuple<int, int, int>& b) {
    if (get<1>(a) != get<1>(b)) {
        return get<1>(a) > get<1>(b);
    }

    return get<2>(a) < get<2>(b);
}
map<int, pair<int, int>> mp;
int main(){
    cin >> n >> c;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        if (mp.find(x) == mp.end()) {
            mp[x] = {i, 1};         
        } else {
            mp[x].second++;          
        }
    }

    vector<tuple<int, int, int>> v;
    for (auto &it : mp) {
        v.push_back({it.first, it.second.second, it.second.first});
    }
    sort(v.begin(), v.end(), cmp);

    for (auto &it : v){
        for (int i = 0; i < get<1>(it); i++) {
            ret+=to_string(get<0>(it));
            ret+=" ";
        }
    }

    cout << ret << "\n";

    return 0;
}
#include<bits/stdc++.h>
using namespace std;
int t;
int n;
map<string, map<string, string>> mp;
int main(){
    cin >> t;

    string result;
    while (t--) {
        cin >> n;
        for (int i = 0; i < n; i++) {
            string wear, type;
            cin >> wear >> type;
            mp[type][wear];
        }

        // 헤드기어 : 모자, 터번
        // 안경 : 선글라스
        // 모자, 터번, 모자 선글라스, 터번 선글라스, 선글라스 = 5가지
        // 벌거벗지 않아야 한다.
        // 헤드기어 = 선택x, 모자, 터번 / 안경 = 선택x 선글라스
        // 3 * 2 = 6 - 1;
        int ret =1;
        for (auto it : mp) {
            ret *= (it.second.size() + 1);
        }
        result+=to_string(ret - 1);
        result+="\n";
        mp.clear();
    }
    cout << result << "\n";
    return 0;
}
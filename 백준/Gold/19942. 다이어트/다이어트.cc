#include <bits/stdc++.h>
using namespace std;
int n;
int ig[20][5];
int a, b, c, d;
int mi = INT_MAX;
string s;
vector<vector<int>> ret;
int main() {
    cin >> n;
    cin >> a >> b >> c >> d;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 5; j++) {
            cin >> ig[i][j];
        }
    }
    // 15 C 6 = 7 13 11 5 = 55 * 91 = 
    // n개중 몇개 선택해서 a,b,c,d 이상을 만들어야하면서 가격을 제일 낮게 해야함
    // 최악의 경우의 수는 2 ^ 15 = 3만정도네 
    for (int i = 0; i < (1 << n); i++){
        int cost = 0;
        int as = 0, bs = 0, cs = 0, ds = 0;
        vector<int> temp;
        for (int j = 0; j < n; j++) {
            if (i & (1 << j)) {
                cost+=ig[j][4];
                as+=ig[j][0];
                bs+=ig[j][1];
                cs+=ig[j][2];
                ds+=ig[j][3];
                temp.push_back(j + 1);
            }
        }
        if (as >= a && bs >= b && cs >= c && ds >= d) {
            if (cost < mi) {
                ret.clear();
                mi = cost;
                ret.push_back(temp);
            } else if (cost == mi) {
                // n의 범위가 10이 넘어서
                // 2 8 9 vs 2 8 10하면 2 8 10이 이김
                ret.push_back(temp);
            }
        }
    }
    sort(ret.begin(), ret.end());

    if (mi == INT_MAX) {
        cout << -1 << "\n";
    } else {
        cout << mi << "\n";
        for (int i : ret[0]) {
            cout << i << " ";
        }
    }

    return 0;
}
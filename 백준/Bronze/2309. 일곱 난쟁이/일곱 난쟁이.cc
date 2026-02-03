#include <bits/stdc++.h>
using namespace std;
vector<int> d;
vector<int> v;
void combi(int start, vector<int> &v){
    if (v.size() == 7) {
        int sum = 0;
        string result;
        for (int i : v) {
            sum+=d[i];
            result += to_string(d[i]);
            result += "\n";
        }
        if (sum == 100) {
            cout << result;
            exit(0);
        }

        return;
    }

    for (int i = start + 1; i < (int) d.size(); i++) {
        v.push_back(i);
        combi(i, v);
        v.pop_back();
    }

    return;
}
int main(){
    // 입력
    for (int i = 0; i < 9; i++) {
        int temp;
        cin >> temp;
        d.push_back(temp);
    }
    // 9명중 7명 키의 합은 100
    // 9C7 => 9 8 / 3 2 1 = 12
    sort(d.begin(), d.end());
    combi(-1, v);
    return 0;
}
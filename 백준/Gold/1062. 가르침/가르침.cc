#include <bits/stdc++.h>
using namespace std;
int n, k;
int cnt = INT_MIN;
vector<string> v;
const char need[] = {'a', 'c', 'n', 't', 'i'};
void comb(int start, int picked, int mask) {
    if (picked == k) {
        int count = 0;
        for (string s : v) {
            bool can = true;
            for (int j = 0; j < (int)s.size(); j++){
                if (mask & (1 << (s[j] - 'a'))) continue;
                can = false;
                
            }
            if (can) {
                count++;
            }
        }
        cnt = max(cnt, count);
        return;
    }
    for (int c = start + 1; c <= 26; c++) {
        if (mask & (1 << c)) continue;

        comb(c, picked + 1, mask | (1 << c));
    }

}
int main(){
    cin >> n >> k;
    for (int i = 0; i < n; i++){
        string word;
        cin >> word;
        v.push_back(word);
    }
    // 필수도 못배우면 아무것도 못읽음
    if (k < 5) {
        cout << 0 << '\n';
        return 0;
    }
    if (k == 26) {
        cout << n << '\n';
        return 0;
    }

    int mask = 0;
    for (char c : need) {
        mask |= 1 << (c - 'a');
    }
    comb(- 1, 5, mask);
    cout << cnt << '\n';
    return 0;
}
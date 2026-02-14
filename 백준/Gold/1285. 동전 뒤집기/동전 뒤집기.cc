    #include <bits/stdc++.h>
    using namespace std;
    int n;
    char mp[21][21];
    int coin[21];
    string s;
    int min_turn = INT_MAX;
    int min_cnt = INT_MAX;
    vector<pair<int, int>> v;
    // 앞면 H, 뒷면 T
    // 행 뒤집음을 바탕으로 열을 탐색, 열에 뒷면이 더 많으면 뒤집었다고 가정해서 더 적은 것을 누적 뒷면을 더 적게 만들어야하니까
    int calculate() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            int r = 0;
            for (int j = 0; j < n; j++){
                if (coin[j] & (1 << i)) continue;
                r++;
            }
            count += min(r, n - r);
        }

        return count;
    }
    // H H W W W 앞면이 두개, 뒷면이 3개인데 -> 앞면이 세개, 뒷면이 두개
    void turn_row(int idx) {
        for (int i = 0; i < n; i++) {
            coin[idx] ^= (1 << i);
        }
    }
    void go(int start) {
        if (start == n) {
            int ret = calculate();
            min_cnt = min(min_cnt, ret);
            return;
        }
        
        go(start + 1);
        coin[start] = ~coin[start];
        go(start + 1);
        // 원복
        coin[start] = ~coin[start];
    }
    int main(){
        cin >> n;
        for (int i = 0; i < n; i++){
            cin >> s;
            for (int j = 0; j < n; j++){
                if (s[j] == 'H') {
                    coin[i] |= (1 << j);
                }
            }
        }

        // 재귀로 0, 1 ... n 행 혹은 0 1 ... n열을 토글연산 수행
        go(0);
        cout << min_cnt << '\n';
        return 0;
    }
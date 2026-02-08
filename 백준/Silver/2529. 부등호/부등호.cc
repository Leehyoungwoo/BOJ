#include <bits/stdc++.h>
using namespace std;
int k;
char a[10];
int b[10];
vector<string> ret;
bool valid() {
    for (int i = 0; i < k; i++) {
        // i번째가 i+1보다 작아야 하는데 크면 false;
        if (a[i] == '<') {
            if (b[i] > b[i + 1]) {
                return false;
            }
        } else {
            // i번쨰가 i+1보다 커야 하는데 작으면 false;
            if (b[i] < b[i + 1]) {
                return false;
            }
        }
    }
    return true;
}

void make_per(int n, int r, int depth) {
    // nPr 순열을 구하고 
    if (r == depth) {
        // 유효성 검사를 통과하면 
        if (valid()) {
            string s;
            // 붙여서 
            for (int i = 0; i < k + 1; i++){
                s+=to_string(b[i]);
            }
            // ret에 넣어주고
            ret.push_back(s);
        }
        return;
    }
    for (int i = depth; i < n; i++) {
        swap(b[i], b[depth]);
        make_per(n, r, depth + 1);
        swap(b[i], b[depth]);
    }
}
int main() {
    cin >> k;
    for (int i = 0; i < k; i++){
        cin >> a[i];
    }
    for (int i = 0; i <= 9; i++) {
        b[i] = i;
    }
    make_per(10, k + 1, 0);
    // 정렬해서 제일 큰거, 작은거 출력
    sort(ret.begin(), ret.end());
    cout << ret[(int)(ret.size()) - 1] << "\n";
    cout << ret[0] << "\n";
    return 0;
}
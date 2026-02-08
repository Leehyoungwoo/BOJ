#include <bits/stdc++.h>
using namespace std;
int k;
char a[10];
int b[10];
vector<string> ret;
bool valid() {
    for (int i = 0; i < k; i++) {
        if (a[i] == '<') {
            if (b[i] > b[i + 1]) {
                return false;
            }
        } else {
            if (b[i] < b[i + 1]) {
                return false;
            }
        }
    }
    return true;
}

void make_per(int n, int r, int depth) {
    if (r == depth) {
        // for (int i = 0; i < r; i++) cout << b[i] << " ";
        // cout << "\n";
        if (valid()) {
            string s;
            for (int i = 0; i < k + 1; i++){
                s+=to_string(b[i]);
            }
            // cout << s << "\n";
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
    sort(ret.begin(), ret.end());
    cout << ret[(int)(ret.size()) - 1] << "\n";
    cout << ret[0] << "\n";
    return 0;
}
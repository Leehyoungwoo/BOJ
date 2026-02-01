#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    vector<int> card;

    cin >> n;
    card.reserve(n);
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        card.push_back(x);
    }

    sort(card.begin(), card.end());

    cin >> m;

    string out;
    out.reserve(m * 2);

    for (int i = 0; i < m; i++) {
        int x; cin >> x;
        int cnt = upper_bound(card.begin(), card.end(), x)
                - lower_bound(card.begin(), card.end(), x);
        out += to_string(cnt);
        out += ' ';
    }

    cout << out;
    return 0;
}
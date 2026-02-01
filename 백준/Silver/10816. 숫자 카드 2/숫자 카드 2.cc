#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> card;
vector<int> target;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        card.push_back(x);
    }

    cin >> m;
    for (int i = 0; i < m; i++) {
        int x; cin >> x;
        target.push_back(x);
}

    sort(card.begin(), card.end());
    for (int i = 0; i < m; i++) {
        cout << upper_bound(card.begin(), card.end(), target[i]) - lower_bound(card.begin(), card.end(), target[i]) << ' ';
    }

    return 0;
}
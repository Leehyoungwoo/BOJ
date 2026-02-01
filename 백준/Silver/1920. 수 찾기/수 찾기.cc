#include <bits/stdc++.h>
using namespace std;
int n, m;
vector<int> num;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        num.push_back(x);
    }

    sort(num.begin(), num.end());
    cin >> m;
    string result;
    for (int i = 0; i < m; i++) {
        int target;
        cin >> target;
        
        bool exists = binary_search(num.begin(), num.end(), target);
        if (exists) {
            result+="1\n";
        } else {
            result+="0\n";
        }
    }

    cout << result;
}
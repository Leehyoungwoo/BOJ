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
    result.reserve(m * 2); 
    for (int i = 0; i < m; i++) {
        int target;
        cin >> target;
        
        int count = upper_bound(num.begin(), num.end(), target) - lower_bound(num.begin(), num.end(), target);
        if (count == 0) {
            result+="0\n";
        } else {
            result+="1\n";
        }
    }

    cout << result;
}
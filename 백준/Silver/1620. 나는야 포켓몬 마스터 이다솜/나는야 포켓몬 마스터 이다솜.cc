#include<bits/stdc++.h>
using namespace std;
int n, m;
// 1 <= n, m <= 100000
map<string, int> mp;
string arr[1000004]; 
int main (){
    cin >> n;
    cin >> m;
    for (int i = 1; i <= n; i++) {
        string name;
        cin >> name;
        mp[name] = i;
        arr[i] = name;
    }

    string result;
    for (int i = 0; i < m; i++) {
        string temp;
        cin >> temp;
        if (isdigit(temp[0])) {
            int idx = stoi(temp);
            result+=arr[idx];
        } else {
            result+=to_string(mp[temp]);
        }
        result+="\n";
    }

    cout << result << "\n";

    return 0;
}
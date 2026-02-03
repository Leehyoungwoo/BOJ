#include<bits/stdc++.h>
using namespace std;
int a[26];
int n;
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        string name;
        cin >> name;
        a[name[0] - 'a']++;
    }
    string result;
    for (int i = 0; i < 26; i++) {
        if (a[i] >= 5) {
            result+=(char) (i + 'a');
        }
    }

    if (result.empty()) cout << "PREDAJA";
    else cout << result;
    
    return 0;
}
#include<bits/stdc++.h>
using namespace std;
map<int, int> fee;
int t[101] = {0};
int main() {
    for (int i = 1; i <= 3; i++) {
        int f;
        cin >> f;
        fee[i] = f;
    }

    for (int i = 0; i < 3; i++) {
        int start, end;
        cin >> start >> end;
        for (int j = start; j < end; j++) {
            t[j]++;
        }
    }
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
        sum += t[i] * fee[t[i]];
    }
    
    cout << sum << "\n";

    return 0;
}
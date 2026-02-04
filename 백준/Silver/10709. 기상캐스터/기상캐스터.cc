#include<bits/stdc++.h>
using namespace std;
int h, w;
int main(){
    cin >> h >> w;
    int result[h][w];
    char g[h][w];
    for (int i = 0; i < h; i++) {
        string input;
        cin >> input;
        for (int j = 0; j < w; j++) {
            g[i][j] = input[j];
        }
    }
    for (int i = 0; i < h; i++) {
        int lastC = -1;
        for (int j = 0; j < w; j++) {
            if (g[i][j] == '.' && lastC == -1) {
                result[i][j] = -1;
            } else if (g[i][j] == 'c') {
                result[i][j] = 0;
                lastC = j;
            } else {
                result[i][j] = j - lastC;
            }
        }
    }

    for (int i = 0; i < h; i++) {
        int lastC = -1;
        for (int j = 0; j < w; j++) {
            cout << result[i][j] << " "; 
        }
        cout << "\n";
    }

    return 0;
}
#include <bits/stdc++.h>
using namespace std;
int n;
string s;
map<int, int> sc;
map<int, int> sum;
int wt[3] =  {-1, -1, -1};
int winner = 0;
vector<string> split(const string &a, string delimiter) {
    vector<string> ret;
    int start = 0;
    int end = a.find(delimiter);
    while (end != string::npos) {
        ret.push_back(a.substr(start, end - start));
        start = end + delimiter.length();
        end = a.find(delimiter, start);
    }
    ret.push_back(a.substr(start));
    
    return ret;
}
int conv(string & s) {
    vector<string> t = split(s, ":");
    return 60 * stoi(t[0]) + stoi(t[1]);
}
string convtoint(int i) {
    string ret;
    int m = i / 60;
    int s = i % 60;
    if (m < 10) ret = "0" + ret;
    ret += to_string(m) + ":";
    if (s < 10) ret+="0";
    ret+=to_string(s);

    return ret;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n;

    for (int i = 0; i < n; i++) {
        // cout << sum[1] << " : "  << sum[2] << "\n";  
        // cout << wt[1] << " : "  << wt[2] << "\n";  
        // cout << sc[1] << " : " << sc[2] << "\n";
        // cout << " ----------------------"<< "\n"; 
        int no;
        cin >> no;
        cin >> s;
        // 득점 시간
        int curtime = conv(s);
        // cout << no << "\n";
        // cout << curtime << "\n";
        // s분에 no팀이 골 넣음 
        sc[no] = sc[no] + 1;
        
        // cout << "이기고 있는 사람 : " << winner << "\n";
        // 동점인 경우 => 둘다 승자 아님
        if (sc[no] == sc[3 - no]) {
            sum[winner] += curtime - wt[winner];
            winner = 0;
            wt[no] = -1;
            wt[3 - no] = -1;
            // cout << "동점 !! " << winner<<"\n";
            continue;
        }

        // cout << "승자 : 득점자 = " << winner << " : " <<no <<"\n";
        // 3 - no
        // 승자가 생김 or 바뀜
        if (sc[no] < sc[3 - no]) {
            continue;
        }
        if (winner != no) {
            winner = no;
            // 승자의 시간 기록
            wt[winner] = curtime;
            // 지난 승자의 기록을 누적 지난 승리 기점 ~ 지금까지
            // 지금까지 승패가 없으면 continue
            if (wt[3 - winner] == -1) {
                continue;
            } 
            // 아니면 누적
            sum[3 - winner] += curtime - wt[3 - winner];
            wt[3 - winner] = -1;
        }

        // 승자가 그대로인 경우 => 뭔가 조작할 필요가 없지 않나?
    }
    
    string fi = "48:00";
    for (int i = 1; i <= 2; i++) {
        if (wt[i] == -1) {
            continue;
        }
        // cout << conv(fi) << " : " << wt[i]; 
        sum[i] += conv(fi) - wt[i];
    }

    cout << convtoint(sum[1]) << "\n";
    cout << convtoint(sum[2]);

    return 0;
}
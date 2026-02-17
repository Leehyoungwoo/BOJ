#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int w, m, n;
int a[1001], b[1001];
long long ret;
map<int,int> pa, pb;
void build(int arr[], int sz, map<int,int>& mp){
    int total = 0;
    for(int i=0;i<sz;i++) total += arr[i];

    mp[0]++;            
    mp[total]++;     

    for(int i=0;i<sz;i++){
        int sum = 0;
        for(int len=1; len<=sz-1; len++){
            sum += arr[(i + len - 1) % sz];
            mp[sum]++;
        }
    }
}

int main(){
    cin >> w;
    cin >> m >> n;

    for(int i=0;i<m;i++) cin >> a[i];
    for(int i=0;i<n;i++) cin >> b[i];

    build(a, m, pa);
    build(b, n, pb);

    for (auto &it : pa){
        int key = it.first;
        int value = it.second;
        auto jt = pb.find(w - key);
        if(jt != pb.end()){
            ret += 1ll * value * jt->second;
        }
    }

    cout << ret << "\n";
    return 0;
}
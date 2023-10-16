N = int(input())

def fact(N):
    if N == 0:
        return 1
    
    return N * fact(N-1)


a = list(str(fact(N)))
a.reverse()

count = 0
for s in a:
    if s != '0':
        break 
    else:
        count+=1
    
print(count)
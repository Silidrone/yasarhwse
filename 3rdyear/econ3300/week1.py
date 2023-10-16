# This was not an assignment, I just did it for fun and for the ease of calculating when doing my homework.

def fv(i, N, P):
    return P * pow(1 + i, N)

def total_fv(i, N, values):
    sum = 0;
    for n in range(0, N + 1):
        sum += fv(i, N - n, values[n])
    
    return sum

money = [300.00, 7712.77, 7712.77, 7712.77, 7712.77]
i = 0.09
N = 4
print(total_fv(i, N, money))

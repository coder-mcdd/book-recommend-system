def swap(var):
    print(var,id(var)) # 2 [11] 2091618587008
    var += [22] #3  [11, 22] 2091618587008
    print(var,id(var)) #4  [11, 22] 2091618587008

var_a = [11]
print(var_a,id(var_a)) #1 [11] 2091618587008
swap(var_a)
print(var_a,id(var_a))


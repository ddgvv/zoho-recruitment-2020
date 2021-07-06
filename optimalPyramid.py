value = input()
length = len(value)
middle = int(length/2);
start = value.split(value[middle])
result = value[middle:] + start[0]
for i in range(length):
    print (" "*(length-i-1)+result[:i+1])

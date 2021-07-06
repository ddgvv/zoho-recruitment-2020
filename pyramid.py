value = input()
length = len(value)
middle = int(length/2);
for i in range(length):
    maxVal = middle+i
    result = ""
    if maxVal < length:
        result = value[middle:middle+i+1]
    else :
        start =  maxVal % length
        result = value[middle:] + value[:start+1]
    print (" "*(length-i-1)+result)
    

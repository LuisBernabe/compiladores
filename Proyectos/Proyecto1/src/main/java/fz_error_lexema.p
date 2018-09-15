ñ
n=0; 
while(n<20):

    if n % 3 == 0 and n % 5 == 0:
        print 'FizzBuzz'
    elif n % 3 == 0:
        print 'Fizz'
    elif n % 5 == 0:
        print 'Buzz'
    else:
        print str(n)
    n+=1   
import sys 
import subprocess 
import os 
 
prog = r'''
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
int main(void)
{
    // Get the bounds for our function:
    int a, b, startBound, endBound;
    scanf("%d %d", &a, &b);
    startBound = (a > b) ? b : a;
    endBound = (a < b) ? b : a;
    
    // If the bounds are valid, call the function:
    if(startBound <= endBound && startBound >= 0 && endBound >= 0 &&
    endBound <= INT_MAX && startBound <= INT_MAX) 
    {
        // Assign the big 'ol sieve.
        bool * sieve = calloc((endBound/2),  1);
        int endBoundSqrt = sqrt(endBound);
        sieve[0] = true;

        // Start at three; because we don't need to consider even numbers.
        for(int index = 3; index <= endBoundSqrt; index += 2)
        {
            if(!sieve[index/2])
            {
                for(int removeIndex = index * index;
                    removeIndex <= endBound;
                    removeIndex = removeIndex + (2 * index))
                {
                    sieve[removeIndex/2] = true;
                }
            }
        }
        // Get the count:
        int count = (startBound < 2) ? 1 : 0;
        
        if(startBound % 2 == 0)
        {    
            startBound++;
        }
        for(;startBound <= endBound; startBound += 2)
        {
            if(!sieve[startBound/2])
            {
                count++;
            }
        }
    
        // Clean up:
        //free(sieve);
        printf("%d", count);
    }
    return 0;
}
''' 
 
if not os.path.exists('foo'): 
    f = open('foo.c', 'w') 
    f.write(prog) 
    f.close() 
    subprocess.call(["gcc", "foo.c", "-ofoo", '-w', '-O3', '-lm']) 
subprocess.call(["./foo"], stdin = sys.stdin)
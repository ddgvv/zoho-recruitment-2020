Zoho Corporation - Programming Test


1. A positive integer is called 'Binary-Decimal', if it contains only 0s and 1s. 
Example: 101, 11, 1. 
Write a program to represent the given positive integer as a sum of minimum number of 'Binary-Decimal' numbers. 

Input: 
32 
Output: 
11+11+10 
Count : 3

Explanation:

There are many possibilities for representing 32 as a sum of Binary-Decimals. 
Few of the possibilities will be,
10+10+10+1+1
Count : 5

11+10+10+1
Count : 4

11+11+10 
Count : 3

The expected output is [11+11+10], as it has minimum number of Binary-Decimals(Count : 3).

Input: 86
Output: 11+11+11+11+11+11+10+10
Count: 8
11+11+11+11+11+11+11+1+1+1+1+1+1+1+1+1 is wrong. 


2. Given a String with numbers and operators. Perform the operation on the numbers in their respective order. Operator precedence need not be considered. The input string will have the numbers followed by the operators. 

Input: "12345 * + - + " 	
Result: 6 [Explanation: 1 * 2 + 3 - 4 + 5 = 6]

Input: "374291 - - * + -" 	
Result: -8 [Explanation: 3 - 7 - 4 * 2 + 9 - 1 = -8]

Input: "67542 - / + -" 		
Result: 2

3. You are given two sorted integer arrays A and B. The sort order can be ascending or descending. Write a program to create an array C that contains integers from A and B. C should be in ascending order and shouldn't have any duplicates. 
Note: Removal of duplicates and ordering has to be done while adding elements to C. i.e. don't write separate loops to sort and remove duplicates. 

Examples: 
Input: [3, 6, 9], [8, 7, 5] 
Output: [3, 5, 6, 7, 8, 9] 
Input: [9, 9, 6, 3], [5, 7, 8, 9] 
Output: [3, 5, 6, 7, 8, 9] 
Input: [9, 6], [5, 17, 21] 
Output: [5, 6, 9, 17, 21] 

4. Alternate sorting: Given an array of integers, rearrange the array in such a way that the first element is first maximum and second element is first minimum and repeat the same sorting for the subsequent pairs

first element - first maximum
second element - first minimum
third element - second maximum
fourth element - second minimum ....
4 6 3 2 
6 4 3 2
6 2 3 4
6 2 4 3


Example:
Input : {1, 2, 3, 4, 5, 6, 7}
Output : {7, 1, 6, 2, 5, 3, 4}

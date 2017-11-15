# LongestPalindromes

Write an application that finds the 3 longest unique palindromes in a supplied string.   
For the 3 longest palindromes, report the palindrome, start index and length, in descending order of length.  

Example Output  

Given the input string: sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop , the output should be:  
Text: hijkllkjih, Index: 23, Length: 10  
Text: defggfed, Index: 13, Length: 8  
Text: abccba, Index: 5 Length: 6  

Solution:
Find all palindromes of odd/even size with middle at a specific index.
In order to select the longest 3 we do the following steps:
1. take first in the order of size
2. Rest of them are selected using the following logic  
=> Take next in order by size and filter the ones that are contained already in selected ones  
=> Example : aaaaaaaabbbccc  
    The response  is aaaaaaaa bbb ccc, but checking palindromes we can find all smaller subtrings of aaaaaaaa.  
    We need to filter them and select unique ones.
    

Testing:
sbt test
Running:
sbt run (will run with example test)  
sbt "run yourstring" for your own test  
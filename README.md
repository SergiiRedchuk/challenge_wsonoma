# WSI Coding Challenge

## Regarding solution

First I thought I'd use known Merge Sort algorithm and modify it to merge the ranges during sorting them.
Then I dropped the idea, so I avoid recursion and keep the solution stack invariant.
So I went with pre-sorting the input range using the efficient Tim Sort with following another pass to merge the sorted ranges.

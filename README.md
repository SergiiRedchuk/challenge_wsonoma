# WSI Coding Challenge

## Regarding requirements and input data

First I assumed that all input ranges will always be valid (rangeStart <= rangeEnd)
and decided to keep the code simpler, but second I thought I'll add handling of invalid ranges to show I care,
despite it was not specified in requirements, and usually I'd ask BA/PO/SME for clarifications.

## Regarding solution

First I thought I'd use known Merge Sort algorithm and modify it to merge the ranges during sorting them.
Then I dropped the idea, so I avoid recursion and keep the solution stack invariant.
So I went with pre-sorting the input range using the efficient Tim Sort with following another pass to merge the sorted ranges.

Pleas don't mind the auxiliary gradle project files and see the solution and the unit tests at:
* [challenge.wsonoma.ZipRangesNormalizer](src/main/java/challenge/wsonoma/ZipRangesNormalizer.java)
* [challenge.wsonoma.ZipRangesNormalizerTest](src/test/java/challenge/wsonoma/ZipRangesNormalizerTest.java)

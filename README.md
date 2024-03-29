# WSI Coding Challenge

## Regarding solution

First I thought I'd use known Merge Sort algorithm and modify it to merge the ranges during sorting them.
Then I dropped the idea, so I avoid recursion and keep the solution stack invariant.
So I went with pre-sorting the input range using the efficient Tim Sort algorithm followed by another one pass to merge the sorted ranges.
The pass for merging the sorted ranges does not consume any additional memory except single memory allocation operation afterwards before returning the merged ranges.

## Build

```
./gradlew shadowJar
```

## Usage

Prepare a CSV file with zip ranges. There is one in the project: src/test/resources/zip_ranges.csv

Run:
```
java -jar build/libs/zip_ranges_normalizer-1.0-dist.jar src/test/resources/zip_ranges.csv
java -jar build/libs/zip_ranges_normalizer-1.0-dist.jar src/test/resources/zip_ranges.csv > normalized_ranges.csv 2> invalid_ranges.txt
```

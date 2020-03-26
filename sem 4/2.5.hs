module SumOfThree where

sumOfTwo :: [Int] -> [Int] -> [Int]
sumOfTwo [] list = list
sumOfTwo list [] = list
sumOfTwo (x:list) (y:list') = (x + y) : sumOfTwo list list'

sumOfThree :: [Int] -> [Int] -> [Int] -> [Int]
sumOfThree list list' list'' = sumOfTwo (sumOfTwo list list') list''
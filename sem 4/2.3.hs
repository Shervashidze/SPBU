module SumOfDigits where

sumOfDigits :: [Int] -> Int
sumOfDigits [] = 0
sumOfDigits (x:xs) = x + sumOfDigits xs
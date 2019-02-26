module SumOfThree where

sumOfThree :: [Int] -> [Int] -> [Int] -> [Int]
sumOfThree [] [] [] = []
sumOfThree first second third = [(if first == [] then 0 else head first) + (if second == [] then 0 else head second) + (if third == [] then 0 else head third)] 
     ++ sumOfThree (if first == [] then [] else tail first) (if second == [] then [] else tail second) (if third == [] then [] else tail third)
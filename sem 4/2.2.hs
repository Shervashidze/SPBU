module DegreesOfTwo where

infinityDoubler :: [Int] -> [Int]
infinityDoubler [] = []
infinityDoubler (x:xs) = [x] ++ infinityDoubler (map (\x -> 2*x) xs)

degreesOfTwo :: Int -> [Int]
degreesOfTwo 0 = []
degreesOfTwo n = take n (infinityDoubler [1, 1..])
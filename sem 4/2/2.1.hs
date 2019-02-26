module ListReverser where

reverser :: [a] -> [a]
reverser [] = []
reverser [x] = [x]
reverser (x:xs) = reverser xs ++ [x]

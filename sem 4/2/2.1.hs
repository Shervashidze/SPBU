module ListReverser where

reverser :: [a] -> [a]
reverser [] = []
reverser (x:xs) = reverser xs ++ [x]

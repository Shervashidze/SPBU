module ListReverser where

reverser :: [a] -> [a]
reverser list = reverser' list [] where
     reverser' [] list = list
     reverser' (x:xs) list = reverser' xs (x:list)

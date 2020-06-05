fibonacci :: Integer -> Integer
fibonacci n = search n 1 1 where
    search n current previous 
        | (n == 0) || (n == 1) = current
        | (n > 0) = search (n - 1) (current + previous) current
        | otherwise = search (n + 1) previous (current - previous)
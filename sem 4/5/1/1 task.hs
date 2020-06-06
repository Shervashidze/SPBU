decomposition :: Int -> [[Int]]
decomposition x = decompose' x 1 where
    decompose' 0 _ = [[]]
    decompose' sum current = [x : y | x <- [current..sum], y <- (decompose' (sum - x) x)] 
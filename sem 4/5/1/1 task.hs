decomposition :: Int -> [[Int]]
decomposition x = decompose' x 1 where
    decompose' 0 _ = [[]]
    decompose' curSum curMin = [x : y | x <- [curMin..curSum], y <- (decompose' (curSum - x) x)]

matrix :: Int -> [[Int]]
matrix 0 = []
matrix n = (map (++ [n]) (matrix (n - 1))) ++ [take n [n, n..]]
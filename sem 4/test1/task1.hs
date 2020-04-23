alternatingOnes :: [Int]
alternatingOnes = 1 : (-1) : alternatingOnes

alternatingList = zipWith (*) alternatingOnes [1..]
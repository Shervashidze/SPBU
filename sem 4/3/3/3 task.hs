findMaxIndex = (fst . findMaxElement . zip [0..]) where
    findMaxElement [x] = x
    findMaxElement (x:y:tail) = findMaxElement ((if snd x > snd y then x else y) : tail)

findMaxIndexInSumList list = findMaxIndex (zipWith (+) list (0:list))
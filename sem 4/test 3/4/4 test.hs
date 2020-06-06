groupElems :: Eq a => [a] -> [[a]]
groupElems [] = []
groupElems (head:tail) = accumulator tail [head] []
    where 
        accumulator [] acc result  = reverse (acc:result)
        accumulator (head:tail) (repetitiveElem:acc) result
            | head == repetitiveElem = accumulator tail (repetitiveElem:repetitiveElem:acc) result
            | otherwise = accumulator tail [head] ((repetitiveElem:acc):result)
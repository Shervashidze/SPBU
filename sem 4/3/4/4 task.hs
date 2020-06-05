reduce :: [Char] -> [Char]
reduce [] = []
reduce [s] = [s]
reduce (x:y:xs) 
    | isSameType x y = reduce xs
    | otherwise = if reduced == y:xs 
        then x:y:xs
        else reduce (x:reduced)
            where reduced = reduce (y:xs)

isSameType x y = (x == '(' && y == ')') || (x == '[' && y == ']') || (x == '{' && y == '}')

isCorrect seq = [] == reduce seq
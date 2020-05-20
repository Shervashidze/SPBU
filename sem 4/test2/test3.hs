squareLine size level 
    | size == level = replicate size '*' ++ "\n"
    | level == 1 = replicate size '*' ++ "\n"
    | otherwise = "*" ++ replicate (size - 2) ' ' ++ "*" ++ "\n"

square size = square' size 1 where
    square' size level = if size == level 
        then squareLine size size
        else (squareLine size level) ++ (square' size (level + 1))

printSquare = putStr . square
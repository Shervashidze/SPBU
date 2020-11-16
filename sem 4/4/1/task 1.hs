filterEven = length . filter even

mapEven list = length list - (sum $ map (\x -> mod x 2) list)

foldrEven :: [Integer] -> Integer
foldrEven = foldr (\x acc -> if even x then (acc + 1) else acc) 0
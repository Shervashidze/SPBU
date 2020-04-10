checkList :: [a] -> (a -> Bool) -> Bool
checkList [] function = False
checkList list function = foldr (&&) True (map function list)
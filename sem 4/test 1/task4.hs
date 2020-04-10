supermap :: [a] -> (a -> [b]) -> [b]
supermap [] actions = []
supermap (head:tail) actions = actions head ++ supermap tail actions
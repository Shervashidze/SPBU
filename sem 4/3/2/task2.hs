numbers = 1 : 7 : 9 : concat (map (addLast (take 3 numbers)) numbers) where
    addLast list number = map (+(number * 10)) list
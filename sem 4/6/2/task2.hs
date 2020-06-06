import System.Random

data BST a = Null | Node a (BST a) (BST a) deriving Show


add :: (Ord a) => a -> BST a -> BST a
add newValue Null = Node newValue Null Null
add newValue (Node value left right) | newValue == value = Node value left right
                                     | newValue > value  = Node value left (add newValue right)
                                     | newValue < value  = Node value (add newValue left) right


capacity :: BST a -> Int
capacity Null = 0
capacity (Node _ left right) = 1 + (capacity left) + (capacity right)


findElem :: (Ord a) => a -> BST a -> Bool
findElem elem Null = False
findElem elem (Node value left right) | elem == value = True
                                      | elem > value  = findElem elem right
                                      | elem < value  = findElem elem left


height :: BST a -> Int
height Null = 0
height (Node _ left right) = 1 + max (height left) (height right)


randomChange :: BST Integer -> IO (BST Integer)
randomChange Null = return Null
randomChange (Node value left right) = do {
        value <- randomIO :: IO Integer;
        left  <- randomChange left;
        right <- randomChange right;
        return $ Node value left right
        }


remove :: (Ord a) => a -> BST a -> BST a
remove elem Null = Null
remove elem (Node value left right) | elem == value = removeNode (Node value left right)
                                    | elem > value  = Node value left (remove elem right)
                                    | elem < value  = Node value (remove elem left) right
                                
removeNode :: BST a -> BST a
removeNode (Node _ Null Null) = Null
removeNode (Node _ Null right) = right
removeNode (Node _ left Null) = left
removeNode (Node _ left right) = Node (findMax left) (removeMax left) right

findMax :: BST a -> a
findMax (Node value _ Null) = value
findMax (Node _ _ right) = findMax right

removeMax :: BST a -> BST a
removeMax (Node _ left Null) = left
removeMax (Node value left right) = Node value left (removeMax right)


test1 = Node 5 (Null) (Null)
test2 = add 7 test1
test3 = add 14 test2
test4 = add 1 test3
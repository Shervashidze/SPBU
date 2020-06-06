import Data.Foldable

data Tree a = Leaf a | Node (Tree a) a (Tree a) | Empty

instance Foldable Tree where
    foldr f acc Empty = acc
    foldr f acc (Leaf x) = f x acc
    foldr f acc (Node l x r) = foldr f (f x (foldr f acc r)) l

--test trees
testTree = Node (Node (Leaf "42") "3" (Leaf "1")) "4" (Node (Leaf "1") "5" (Leaf "88"))
emptyTree = Empty

getElems :: Tree a -> [a]
getElems tree = foldr (:) [] tree
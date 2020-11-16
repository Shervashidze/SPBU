import Control.Monad.State
import Data.List

data Graph v = Graph [(Int,v)] [(Int,Int,Int)] deriving (Show)
data Distance v = Distance (v, Int) deriving (Show)

vertex (Distance a) = fst a
cost (Distance a)   = snd a

first (v, u, w)  = v
second (v, u, w) = u
third (v, u, w)  = w

getVertexes (Graph vertexes edges) = vertexes

getDistance :: [Distance Int] -> Int -> Int
getDistance distances v = cost (head $ filter ((== v) . vertex) distances)

sortByNum :: [Distance Int] -> [Distance Int]
sortByNum ds = sortBy comparator ds

comparator :: Distance Int -> Distance Int -> Ordering
comparator (Distance a) (Distance b) | snd a <= snd b = LT
                                     | otherwise      = GT

updateWeights :: [(Int, Int, Int)] -> [Distance Int] -> [Distance Int]
updateWeights [] distances = distances
updateWeights (x : xs) distances | getDistance distances v + w <= getDistance distances u = 
                                updateWeights xs $ (Distance (u, getDistance distances v + w)) : (filter ((/= u) . vertex) distances)
                             | getDistance distances u + w <= getDistance distances v = 
                                updateWeights xs $ (Distance (v, getDistance distances u + w)) : (filter ((/= v) . vertex) distances)
                             | otherwise = updateWeights xs distances where
                                v = first x
                                u = second x
                                w = third x

initialize :: Int -> [(Int,v)] -> [Distance Int]
initialize start vertexes = [if x == start then Distance (x, 0) else Distance (x, 100000) | x <- map fst vertexes]

dijkstra' :: Graph v -> [Distance Int] -> (State [Int] [Distance Int])
dijkstra' (Graph vertexes edges) distances = do {
        state <- get;
        if null state
                then return distances
                else do 
                        let v = minimumBy (\a b -> compare (getDistance distances a) $ getDistance distances b) state
                        put (filter (/= v) state)
                        dijkstra' (Graph vertexes edges) $ updateWeights (filter (\e -> (first e == v || second e == v)) edges) distances
        }

dijkstra :: Graph v -> Int -> [Distance Int]
dijkstra graph start = sortByNum $ evalState (dijkstra' graph $ initialize start $ getVertexes graph) (map fst $ getVertexes graph)


graph1 = Graph [(1, 'a'), (2, 'b'), (3, 'c'), (4, 'd')] [(1, 2, 8), (1, 3, 1), (3, 4, 22), (2, 3, 10)]


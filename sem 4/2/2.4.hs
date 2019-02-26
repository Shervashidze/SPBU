module FindNumber where

findNumber :: Int -> [Int] -> Int
findNumber _ [] = -1
findNumber x list = find x (zip list [0, 1..])
    where find _ [] = -1
          find x (head:tail) = 
              if (fst head == x) then snd head
              else find x tail
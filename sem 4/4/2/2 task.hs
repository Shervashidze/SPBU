module SortedList where

import System.IO

main = do
    doLoop []

addTo :: [Int] -> Int -> [Int]
addTo [] y = [y]
addTo (head : tail) y 
    | (y < head) = y : head : tail
    | (y == head) = head : tail
    | otherwise = head : addTo tail y

removeFrom :: [Int] -> Int -> [Int]
removeFrom [] y = []
removeFrom list y | not (elem y list) = list
             | elem y list = filter (/=y) list



doLoop :: [Int] -> IO ()
doLoop elements = do
    putStr "Print 1 to add element in list,\n"
    putStr "Print 2 to remove element from list, \n"
    putStr "Print 3 to print list, \nPrint 0 to quit.\n"
    command <- getLine
    
    case command of
        '0' : _ -> return()
        '1' : value -> do
                doLoop (addTo elements (read value::Int))
        '2' : value -> do
                doLoop (removeFrom elements (read value::Int))
        '3' : _ -> do
                print elements
                doLoop elements
        _ -> do
                putStr "Incorrect command, try again.\n"
                doLoop elements 
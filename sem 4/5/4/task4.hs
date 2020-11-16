import Data.List
import Data.Char

data Cell = 
     Cell 
        Float -- coef
        Int -- power

deriveCell :: Cell -> Cell
deriveCell (Cell coef pow) = Cell (coef * (fromIntegral pow)) (pow - 1)

multCell :: Cell -> Cell -> Cell
multCell (Cell coef1 pow1) (Cell coef2 pow2) = Cell (coef1 * coef2) (pow1 + pow2)

instance Show Cell where
    show (Cell 0  _) = "0"
    show (Cell coef  0) = show coef
    show (Cell 1 p) | p > 0       = intercalate "*" (take p vars) 
                    | otherwise   = intercalate "/" ("1" : (take (-p) vars)) where
                                       vars = "x" : vars
    show (Cell (-1) p) = '-' : show (Cell 1  p)
    show (Cell coef p) | p < 0 = show coef ++ (tail $ show (Cell 1 p))
                       | p > 0 = show coef ++ "*" ++ (show (Cell 1 p))


data Expression = Expression [Cell]

simplify :: Expression -> Expression
simplify (Expression cells) = Expression $ filter notNull $ map sum $ groupBy equalPow $ sortBy cmp cells
    where 
        cmp (Cell _ pow1) (Cell _ pow2) = compare pow1 pow2
        equalPow (Cell _ pow1) (Cell _ pow2) = pow1 == pow2
        sum (x:xs) = foldl sum' x xs
        sum' (Cell coef1 pow) (Cell coef2 _) = Cell (coef1 + coef2) pow
        notNull (Cell coef _) = coef /= 0

derive :: Expression -> Expression
derive (Expression cells) = simplify $ Expression (map deriveCell cells)

parse :: String -> Expression
parse s =  Expression (map (foldl multCell (Cell 1 0) . map parseCell . splitByTangled) $ splitByPlusMinus s)
    where
        replace from curr | curr == from = ' '
                          | otherwise    = curr
        splitByPlusMinus s = concatMap (words . map (replace '+')) 
                            $  map (\x -> '-':x) $ words $ map (replace '-') 
                            $ ("0+" ++) $ concat $ words s
        splitByTangled s = concatMap (words . map (replace '*')) 
                            $  map (\x -> '/':x) $ words $ map (replace '/') 
                            $ ("1*" ++) $ concat $ words s
        str2int str = read str :: Float
        parseCell "" = Cell 1 0
        parseCell "/x" = Cell 1 (-1)
        parseCell "x" = Cell 1 1
        parseCell "-x" = Cell (-1) 1
        parseCell ('/':xs) = Cell (1 / (str2int xs)) 0
        parseCell xs = Cell (str2int xs) 0

instance Show Expression where
    show p = if res == "" then "0" else res
        where
            res = show' $ simplify p 
            show' (Expression []) = ""
            show' (Expression (x:xs)) = foldl conc (show x) $ map show xs 
                where
                    conc a "" = a
                    conc a ('-':b) = a ++ ('-':b)
                    conc a b = a ++ "+" ++ b

-- show $ derive $ parse "12.2*x*x*x*x - 5 * x * x + x + 5"
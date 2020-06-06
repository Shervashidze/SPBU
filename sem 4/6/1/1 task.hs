import Data.List

data Term = 
    Term 
        Integer -- coefficient
        Integer -- power

instance Show Term where
    show (Term coef 0) = show coef 
    show (Term coef pow) = showCoef ++ showX where
        showCoef | coef == 1    = ""
                 | coef == -1   = "-"
                 | otherwise    = show coef
        showX = "x" ++ if pow == 1 then "" else ("^" ++ if pow > 0 then show pow else "(" ++ show pow ++ ")")


multTerms :: Term -> Term -> Term
multTerms (Term coef1 pow1) (Term coef2 pow2) = Term (coef1 * coef2) (pow1 + pow2)


data Polynomial = Polynomial [Term]

simplify :: Polynomial -> Polynomial
simplify (Polynomial terms) = Polynomial $ filter removeNullCoef $ map sumSamePow $ groupBy equalPow $ sortBy termsCompare terms
    where
        removeNullCoef (Term coef _) = coef /= 0
        sumSamePow (head:tail) = foldr sumSame' head tail
        sumSame' (Term coef1 p) (Term coef2 _) = Term (coef1 + coef2) p
        equalPow (Term _ p1) (Term _ p2) = p1 == p2
        termsCompare (Term _ p1) (Term _ p2) = compare p1 p2


add :: Polynomial -> Polynomial -> Polynomial
add (Polynomial terms1) (Polynomial terms2) = simplify $ Polynomial (terms1 ++ terms2)

mult :: Polynomial -> Polynomial -> Polynomial
mult (Polynomial terms1) (Polynomial terms2) = simplify $ Polynomial [multTerms x y | x <- terms1, y <- terms2]

instance Show Polynomial where
    show p = show' $ simplify p where
        show' (Polynomial []) = ""
        show' (Polynomial (head:tail)) = foldl addition (show head) $ map show tail 
            where
                addition term1 ('-':term2) = term1 ++ ('-':term2)
                addition term "" = term
                addition term1 term2 = term1 ++ "+" ++ term2


testP = Polynomial [Term 1 2, Term (-2) 4, Term (-42) 0, Term 7 0, Term 0 42, Term 1 (-2)]

import Control.Monad

findLocMax :: Ord a => [a] -> Maybe a
findLocMax (prev:cur:next:end) = mplus (if (cur > prev && cur > next) then (Just cur) else Nothing) (findLocMax $ cur:next:end)
findLocMax _ = Nothing
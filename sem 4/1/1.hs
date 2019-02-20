module Factorial where

factorial 0 = 1
factorial n =
    if n < 2
    then n
    else n * factorial (n - 1)
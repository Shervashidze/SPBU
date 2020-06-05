{--
original function:
func x l = map (\y -> y*x) l

reducing lambda:
func x l = map (*x) l

reducing last arg:
func x = map (*x)
   
reducing x -}
func :: Integer -> [Integer] -> [Integer]
func = map . (*)

{--
original function:
func x l = map (\y -> y*x) l

reducing lambda:
func x 1 = map (*x) 1

reducing last arg:
func x = map (*x)
   
reducing x -}
func :: Integer -> [Integer] -> [Integer]
func = map . (*)
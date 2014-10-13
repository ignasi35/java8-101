def sum = { x, y -> 
  println("summing")
  x+y
}

def memoed = sum.memoize()

println("memoed 1: " + memoed(3,4))
println("memoed 2: " + memoed(3,4))
println("memoed 3: " + memoed(3,5))
println("memoed 4: " + memoed(3,4))


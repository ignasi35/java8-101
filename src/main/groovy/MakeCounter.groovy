def makeCounter() {
  def localVar = 0 
  return { localVar += 1 }
}

c1 = makeCounter()
c1()
c1()
c1()


c2 = makeCounter()

println "C1=${c1()} , C2=${c2()}"

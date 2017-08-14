package p02kmeans

import breeze.linalg._
import p00rkhs.{Gram, Kernel}
import p02kmeans.Base.ComputationState
import p04Various.Iterate

object SimpleExample {
  def main {
    val proportion = DenseVector[Double](0.3, 0.7)
    val paramGenerator = Array(
        Array(
            new Data.GaussianClassParam(0.0, 1.0),
            new Data.GaussianClassParam(10.0, 1.0)),
        Array(
            new Data.GaussianClassParam(0.0, 1.0),
            new Data.GaussianClassParam(10.0, 1.0)))
    val nObs = 100
    val nClass = 2
    
    val nIteration = 100
    
    val data = Data.gaussianMixture(
      proportion,
      paramGenerator,
      nObs)
      
    val gram = Gram.generate(data.data, Kernel.linear) // compute Gram matrix
    val param = Base.init(nObs, nClass) // initialize the algorithm by selecting a representative element per class and setting the class centers using them
    val zeroCompState = new ComputationState(
        0,
        gram,
        param,
        DenseMatrix.zeros[Double](nObs, nClass))
    val initCompState = Base.eStep(zeroCompState) // an e step is performed, so that the ComputationState is valid
    
    val res = Iterate.iterate( // launch the real computation, which alternates E and M steps, updating the computation state
        initCompState,
        Base.emIteration,
        (s: ComputationState) => s.nIteration == nIteration)
    
    val ziComputed = DenseVector.tabulate[Int](nObs)(i => argmax(res.zik(i, ::)))
    val matConf = computeMatConf(nClass, data.zi, ziComputed)
    
    println(matConf)
  }
  
  /**
   * It would be possible to provide an immutable implementation of this function. But it would be cumbersome and
   * would run in log(n). One possible implementation would use a scala Vector which is updated un a fold for example.
   * 
   * I use a for loop because a DenseVector does not provide the zip method (unlike an Array for example. */
  def computeMatConf(
      nClass: Int,
      realClass: DenseVector[Int],
      predictedClass: DenseVector[Int])
  : DenseMatrix[Int] = {
    val nObs = realClass.length
    val matConf = DenseMatrix.zeros[Int](nClass, nClass)
    
    for (i <- 0 to nObs - 1) {
      matConf(realClass(i), predictedClass(i)) += 1
    }
    
    return matConf
  }
}
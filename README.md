# Sernel

Kernel methods in Scala, to learn, at the moment. The current objective is to find the best way to factorize as much code as possible among the various methods that use RKHS.

Sernel is Copyright (c) 2020 Vincent KUBICKI and distributed as a BSD 3-Clause License

# How to use

The easiest way to start is to use Eclipse:
- A ".project" file is provided for easy import.
- Sbt files are provided so that after an "sbt eclipse" everything will run smoothly.
- Scala IDE
    - the eclipse marketplace update site for Scala-IDE is http://download.scala-ide.org/sdk/lithium/e44/scala211/stable/site, but the version is old 4.2
    - Scala IDE provides this site: http://download.scala-ide.org/sdk/lithium/e46/scala212/stable/site
    - the link to the latest update site is provided here: http://scala-ide.org/download/current.html
    - note that e46 refers to the eclipse version

# To Do

## Short term

- In the kernel kmeans, take should not be used, as this might imply saving the results of the first iterations. Should drop and next be used instead ?

## Medium term

- Gram matrix symmetry should be exploited to reduce computation times
- BFGS might not be the best algorithm to exploit the quadratic nature of the Representer Theorem optimisation problem.
    - A more specialized algorithm might help enhance performances.
    - There is a quadratic optimizer in Scala Breeze
- Get a better understanding of how Breeze optimization package works
	- Termination conditions

## Long term

- Use the existing framework to implement other kernel methods:
    - k-means
    - SVM
    - kernel embeddings: https://en.wikipedia.org/wiki/Kernel_embedding_of_distributions
        - kernel two-sample test: http://jmlr.org/papers/volume13/gretton12a/gretton12a.pdf
- Create new kernels from addition, product of kernels, to support heterogeneous multivariate data automatically
- Automate the choice of kernel using an error function to be minimized
    - the error function should be provided as a parameter
    - the kernels could be provided as a list of kernels

# Bugs

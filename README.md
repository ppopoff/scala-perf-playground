# scala-perf-playground
Playground for scala &amp; jmh

JMH:
To get help use
```shell
    > sbt
    > sbt:scala-perf-playground> jmh:run -h
```

Here's how I propose to run the benchmark:
It stands for "20 iterations", "10 warmup iterations", "1 fork" and "1 thread":
```shell
    > sbt
    > sbt:scala-perf-playground> jmh:run -i 20 -wi 10 -f1 -t1

```

From jmh plugin webpage:
> For "real" results we recommend to at least warm up 10 to 20 iterations, and then measure 10 to 20 iterations again. Forking the JVM is required to avoid falling into specific optimisations (no JVM optimisation is really "completely" predictable)

###Links:

####About JMH itself:
 * A good basic explanation of jmh can be found [here](https://shipilev.net/blog/2014/nanotrusting-nanotime/)
 * A [list](https://github.com/openjdk/jmh/tree/master/jmh-core-benchmarks) of core benchmarks
 * [JMH webpage](https://openjdk.java.net/projects/code-tools/jmh/)
 * [Github repo](https://github.com/openjdk/jmh)
 

####JHM Scala plugin:
 * JMH Sbt plugin [here](https://github.com/ktoso/sbt-jmh)
 * Java samples adopted to Scala [here](https://github.com/ktoso/sbt-jmh/tree/master/plugin/src/sbt-test/sbt-jmh/run/src/main/scala/org/openjdk/jmh/samples)

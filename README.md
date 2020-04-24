# Micronaut Framework introduction

#### GraalVM
GraalVM is a universal virtual machine for running applications written in JavaScript, Python, Ruby, R, JVM-based languages like Java, Scala, Groovy, Kotlin, Clojure, and LLVM-based languages such as C and C++. \
GraalVM removes the isolation between programming languages and enables interoperability in a shared runtime. It can run either standalone or in the context of OpenJDK, Node.js or Oracle Database. \
https://www.graalvm.org/docs/why-graal/

#### GraalVM Native image
GraalVM Native Image allows you to ahead-of-time compile Java code to a standalone executable, called a native image. This executable includes the application, the libraries, the JDK and does not run on the Java VM, but includes necessary components like memory management and thread scheduling from a different virtual machine, called “Substrate VM”. Substrate VM is the name for the runtime components (like the deoptimizer, garbage collector, thread scheduling etc.). The resulting program has faster startup time and lower runtime memory overhead compared to a Java VM. \
https://www.graalvm.org/docs/reference-manual/native-image/

#### Substrate VM
Substrate VM is a framework that allows ahead-of-time (AOT) compilation of Java applications under closed-world assumption into executable images or shared objects (ELF-64 or 64-bit Mach-O). \
https://github.com/oracle/graal/tree/master/substratevm

**Graal** - Java bytecode compiler. Can be used just in time (as part of a JVM) or ahead of time. \

**SubstrateVM** - other things (runtime) needed to actually run ahead-of-time compiled Java bytecode without a JVM. This powers the "native-image" command of GraalVM. \

**Truffle** - framework for implementing languages as AST interpreters which can be just-in-time compiled using graal. Some notable languages implemented are JavaScript, Ruby, R and LLVM bitcode. \

**GraalVM** - most of these technologies packaged together in order to support different use cases, for example: running JVM programs (i.e. anything that compiles to Java bytecode) using Graal as the JIT compiler for better peak performance, ahead-of-time compiling JVM programs for fast startup and low memory footprint, running fast dynamic languages (JS, R, Ruby) that can interoperate without overhead, and so on.

##GraalVM installation
Install GraalVM Community Edition \
Download `graalvm-ce-java11-linux-amd64-20.0.0.tar.gz` from https://github.com/graalvm/graalvm-ce-builds/releases. 

Create folder with readable name:
```yaml
 sudo mv graalvm-ce-java11-20.0.0/ /usr/lib/jvm/
 ln -s graalvm-ce-java11-20.0.0 graalvm
```

Set environment variables:
```yaml
export GRAALVM_HOME=/usr/lib/jvm/graalvm
export JAVA_HOME=$GRAALVM_HOME

echo "PATH=$PATH:$GRAALVM_HOME/bin" >> ~/.bash_profile
echo "PATH=$PATH:$GRAALVM_HOME/lib/svm/bin" >> ~/.bash_profile
echo "PATH=$PATH:$GRAALVM_HOME/lib/installer/bin" >> ~/.bash_profile
```

### How to configure project for creating Graal native image

#### Add `svm` and `graal` dependencies:

*build.gradle*
```yaml
compileOnly "org.graalvm.nativeimage:svm"
annotationProcessor "io.micronaut:micronaut-graal" 
```

Add`native-image.properties` file in `resources/META-INF/native-image/com.softwarehut.edu/edu-micronaut` directory.
```
Args = -H:IncludeResources=logback.xml|application.yml|bootstrap.yml \
       -H:Name=edu-micronaut \
       -H:Class=com.softwarehut.edu.Application \
       --initialize-at-build-time=org.postgresql.Driver,org.postgresql.util.SharedTimer \
       --allow-incomplete-classpath \
       --report-unsupported-elements-at-runtime \
       --initialize-at-build-time \
       --no-server \
       --no-fallback
```


Annotate every POJO class with`@Introspected`
>Annotate the class with @Introspected to generate BeanIntrospection metadata at compilation time. This information is use the render the POJO as json using Jackson without using reflection.

Retaining Parameter Names in Controller's methods \
*gradle.build*:
```{caption="sadfsd"}
compileJava.options.compilerArgs += '-parameters'
```

Install `native-image` tool (not included by default) by using command of `GraalVM Updater`.
```
gu install native-image 
```

Build GraalVM Native image:
```
native-image -jar build/libs/edu-micronaut-0.1-all.jar
```

Possible warnings. The following warning means that there is an usage of a class is not present in project classpath.
```bash
Warning: class initialization of class org.apache.logging.log4j.core.async.AsyncLoggerConfigDisruptor failed with exception java.lang.NoClassDefFoundError: com/lmax/disruptor/EventFactory. This class will be initialized at run time because option --allow-incomplete-classpath is used for image building. Use the option --initialize-at-run-time=org.apache.logging.log4j.core.async.AsyncLoggerConfigDisruptor to explicitly request delayed initialization of this class.
```

### Docker
Docker client on Windows Subsystem for Linux:
```
docker -H localhost:2375 images
```

Set environment variable to access docker directly
```
echo "export DOCKER_HOST=localhost:2375" >> ~/.bash_profile
```

## Walkarounds
JPA and Hibernate
- Add `--initialize-at-build-time=org.springframework.jdbc.datasource.ConnectionProxy` to native-image command.
- Add `--allow-incomplete-classpath`
- Add `--initialize-at-build-time=<DRIVER_CLASS_NAME>`

# N-java
This is utility for RxJava. This source was originally part of Ndroid. I separated the part that could only exist in Java.<br/>
This library support <B>Java7</B> and that used <B>RxJava2</B>. 

You can find module in Support Function index : [https://github.com/skaengus2012/N-java#support-function]

# Getting started

<H2>Gradle Project</H2>

<B>STEP1</B> : Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

<B>STEP2</B> : Add the dependency:<br/>
```gradle
dependencies {
    compile 'com.github.skaengus2012:N-java:v0.2.1-beta'
}
```

<H2>Maven Project</H2>

Add it your pom.xml at your project.

```xml
<repositories>
   <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
   </repository>
</repositories>

<dependency>
   <groupId>com.github.skaengus2012</groupId>
   <artifactId>N-java</artifactId>
   <version>v0.2.1-beta</version>
</dependency>
```

# Support Function

<B>1. Lambda combination</B> : https://github.com/skaengus2012/N-java/wiki/N-java-WIKI-!#lambda-combination<br/>
<B>2. MaybeUtil</B> : https://github.com/skaengus2012/N-java/wiki/N-java-WIKI-!#maybeutil<br/>
<B>3. TimeUtil & TimeBuilder </B> : https://github.com/skaengus2012/N-java/wiki/N-java-WIKI-!#timeutil--timebuilder <br/>
<B>4. CheckUtil </B> : https://github.com/skaengus2012/N-java/wiki/OPEN-N-java-v0.2-release-wiki!#checkutil <br/>

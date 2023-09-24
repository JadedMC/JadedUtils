JadedUtils
===========

A collection of commonly used utility methods used throughout the JadedMC Minecraft server.

What's Inside
------
- Chat Utilities
- Item Builders
- Math Utilities
- BukkitScheduler-powered Timers

Requirements
------
- Paper API
- Java 17
- Minecraft 1.20.1

Maven
------
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupID>com.github.JadedMC</groupID>
    <artifactId>JadedUtils</artifactID>
    <version>master-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

Gradle
------
```kotlin
allprojects { 
    repositories { 
        maven { url 'https://jitpack.io' }
    }
}
```

```kotlin
dependencies {
    implementation 'com.github.JadedMC:JadedUtils:master-SNAPSHOT'
}
```
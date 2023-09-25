JadedUtils [![](https://jitpack.io/v/JadedMC/JadedUtils.svg)](https://jitpack.io/#JadedMC/JadedUtils)
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
    <groupId>com.github.JadedMC</groupId>
    <artifactId>JadedUtils</artifactId>
    <version>Tag</version>
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
    implementation 'com.github.JadedMC:JadedUtils:Tag'
}
```
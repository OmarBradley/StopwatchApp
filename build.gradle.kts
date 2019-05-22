buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependency.gradle)
        classpath(Dependency.Kotlin.gradlePlugin)
        classpath(Dependency.Junit.junit5)
    }
}

allprojects {

    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}

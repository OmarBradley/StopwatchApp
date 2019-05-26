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
        maven {
            setUrl("https://dl.bintray.com/soywiz/soywiz")
        }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}

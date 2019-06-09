object Dependency {

    const val gradle = "com.android.tools.build:gradle:${Version.gradle}"

    object Kotlin {

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val stblib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Version.kotlin}"
    }

    object Coroutine {

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"
    }

    object KotlinTest {

        const val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:${Version.kotlinTest}"
    }

    object AndroidX {

        const val appcompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val ktx = "androidx.core:core-ktx:${Version.ktx}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Version.constraintlayout}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Version.recyclerview}"
    }

    object Paging {

        const val runtimeKtx = "androidx.paging:paging-runtime-ktx:${Version.paging}"
        const val runtime = "androidx.paging:paging-runtime:${Version.paging}"
        const val common = "androidx.paging:paging-common-ktx:${Version.paging}"
    }

    object Lifecycle {

        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    }

    object Junit {

        const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:${Version.junit5}"
        const val hamkrest = "com.natpryce:hamkrest:${Version.hamkrest}"
    }

    object Spek {

        const val dslJvm = "org.spekframework.spek2:spek-dsl-jvm:${Version.spek2}"
        const val runnerJunit5 = "org.spekframework.spek2:spek-runner-junit5:${Version.spek2}"
    }

    object AndroidTest {

        const val runner = "androidx.test:runner:${Version.testRunner}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
    }

    object Okhttp3 {

        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp3}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp3}"
    }

    object Retrofit2 {

        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit2}"
        const val coroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.coroutineAdapter}"
        const val gsonConvertor = "com.squareup.retrofit2:converter-gson:${Version.retrofit2}"
    }

    object Koin {

        const val core = "org.koin:koin-core:${Version.koin}"
        const val coreExt = "org.koin:koin-core-ext:${Version.koin}"
        const val test = "org.koin:koin-test:${Version.koin}"
        const val androidxScope = "org.koin:koin-androidx-scope:${Version.koin}"
        const val androidxViewmodel = "org.koin:koin-androidx-viewmodel:${Version.koin}"
    }

    object Glide {

        const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    }

    object Kclock {

        const val jvm = "com.soywiz:klock-jvm:${Version.klock}"
    }


}

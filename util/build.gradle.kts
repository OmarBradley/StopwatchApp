import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    id(Plugin.androidJunit5)
    id(Plugin.kotlinKapt)
    id(Plugin.kotlinExtensions)
}

android {

    compileSdkVersion(AppConfig.Sdk.targetVersion)

    defaultConfig {
        minSdkVersion(AppConfig.Sdk.minVersion)
        targetSdkVersion(AppConfig.Sdk.targetVersion)
        versionCode = AppConfig.Version.code
        versionName = AppConfig.Version.name
        testInstrumentationRunner = AppConfig.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    dataBinding.isEnabled = true
    testOptions {
        junitPlatform.filters.includeEngines("spek2")
    }

    androidExtensions { isExperimental = true }

}

dependencies {
    implementation(Dependency.Kotlin.stblib)
    implementation(Dependency.AndroidX.appcompat)
    implementation(Dependency.AndroidX.ktx)
    implementation(Dependency.AndroidX.constraintlayout)
    implementation(Dependency.AndroidX.recyclerview)
    implementation(Dependency.Glide.glide)
    implementation(Dependency.Coroutine.android)
    implementation(Dependency.Coroutine.core)
    implementation(Dependency.Lifecycle.viewModelKtx)
    implementation(Dependency.Paging.runtimeKtx)
    implementation(Dependency.Paging.common)
    implementation(Dependency.Kclock.jvm)
    implementation(Dependency.Lifecycle.extensions)


    kapt(Dependency.Lifecycle.compiler)
    kapt(Dependency.Glide.compiler)

    testImplementation(Dependency.Spek.dslJvm)
    testImplementation(Dependency.Spek.runnerJunit5)
    testImplementation(Dependency.Kotlin.reflect)
    testImplementation(Dependency.Junit.hamkrest)
    testImplementation(Dependency.KotlinTest.kotlinTest)
}

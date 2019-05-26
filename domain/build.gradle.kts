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
    implementation(Dependency.Coroutine.core)

    testImplementation(Dependency.KotlinTest.kotlinTest)
}

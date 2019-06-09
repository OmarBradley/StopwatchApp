import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id(Plugin.android)
    id(Plugin.kotlinAndroid)
    id(Plugin.kotlinExtensions)
    id(Plugin.kotlinKapt)
    id(Plugin.androidJunit5)
}

android {

    compileSdkVersion(AppConfig.Sdk.targetVersion)

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdkVersion(AppConfig.Sdk.minVersion)
        targetSdkVersion(AppConfig.Sdk.targetVersion)
        versionCode = AppConfig.Version.code
        versionName = AppConfig.Version.name
        testInstrumentationRunner = AppConfig.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    dataBinding.isEnabled = true
    lintOptions.disable("ExtraTranslation")
    testOptions {
        junitPlatform.filters.includeEngines("spek2")
    }
}

dependencies {
    implementation(project(":util"))
    implementation(project(":domain"))
    implementation(Dependency.Kotlin.stblib)
    implementation(Dependency.AndroidX.appcompat)
    implementation(Dependency.AndroidX.ktx)
    implementation(Dependency.AndroidX.constraintlayout)
    implementation(Dependency.AndroidX.recyclerview)
    implementation(Dependency.Paging.runtimeKtx)
    implementation(Dependency.Paging.common)
    implementation(Dependency.Coroutine.android)
    implementation(Dependency.Coroutine.core)

    implementation(Dependency.Koin.core)
    implementation(Dependency.Koin.coreExt)
    implementation(Dependency.Koin.androidxScope)
    implementation(Dependency.Koin.androidxViewmodel)

    implementation(Dependency.Lifecycle.viewModelKtx)
    implementation(Dependency.Lifecycle.extensions)

    kapt(Dependency.Lifecycle.compiler)

    testImplementation(Dependency.Koin.test)
    testImplementation(Dependency.Spek.dslJvm)
    testImplementation(Dependency.Spek.runnerJunit5)
    testImplementation(Dependency.Kotlin.reflect)
    testImplementation(Dependency.Junit.hamkrest)

    androidTestImplementation(Dependency.AndroidTest.runner)
    androidTestImplementation(Dependency.AndroidTest.espressoCore)
}

plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kaptPlugin)
    id(Dependencies.Plugins.hilt)
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = AppConfig.NameSpace.repository

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
    }

    compileOptions {
        sourceCompatibility = AppConfig.javaCompatibility
        targetCompatibility = AppConfig.javaCompatibility
    }

    kotlinOptions {
        jvmTarget = AppConfig.javaJvmTarget
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(Dependencies.Module.dataNetwork))
    implementation(project(Dependencies.Module.dataCache))
    implementation(project(Dependencies.Module.domainRepository))
    implementation(project(Dependencies.Module.domainCommonEntity))

    // Coroutines
    implementation(Dependencies.ThirdParty.kotlinxCoroutinesAndroid)

    // Hilt
    implementation(Dependencies.ThirdParty.hiltAndroid)
    kapt(Dependencies.ThirdParty.hiltKapt)

    // Paging
    implementation(Dependencies.ThirdParty.paging)

    implementation(Dependencies.ThirdParty.roomKtx)

    implementation(Dependencies.ThirdParty.retrofit)

    // Test
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.kotlinxCoroutinesTest)

}
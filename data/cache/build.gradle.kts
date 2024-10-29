plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kaptPlugin)
    id(Dependencies.Plugins.hilt)
    id(Dependencies.Plugins.serialization)
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = AppConfig.NameSpace.cache

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
    implementation(project(Dependencies.Module.domainCommonEntity))

    // Room
    implementation(Dependencies.ThirdParty.roomRuntime)
    implementation(Dependencies.ThirdParty.roomKtx)
    implementation(Dependencies.ThirdParty.roomPaging)
    kapt(Dependencies.ThirdParty.roomCompiler)
    implementation(Dependencies.ThirdParty.serializationJson)

    // Hilt
    implementation(Dependencies.ThirdParty.hiltAndroid)
    kapt(Dependencies.ThirdParty.hiltKapt)

    // Paging
    implementation(Dependencies.ThirdParty.paging)
}
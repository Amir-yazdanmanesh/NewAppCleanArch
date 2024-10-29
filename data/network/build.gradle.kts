plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kaptPlugin)
    id(Dependencies.Plugins.hilt)
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = AppConfig.NameSpace.network

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

    // Coroutines
    implementation(Dependencies.ThirdParty.kotlinxCoroutinesAndroid)

    // Retrofit
    implementation(Dependencies.ThirdParty.retrofit)
    implementation(Dependencies.ThirdParty.loggingInterceptor)
    implementation(Dependencies.ThirdParty.retrofitConverterGson)

    // Hilt
    implementation(Dependencies.ThirdParty.hiltAndroid)
    kapt(Dependencies.ThirdParty.hiltKapt)

    // Paging
    implementation(Dependencies.ThirdParty.paging)

    // Test
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.kotlinxCoroutinesTest)
    testImplementation(Dependencies.Test.okHttp3MockWebServer)
    testImplementation(Dependencies.Test.kotlinTest)
}
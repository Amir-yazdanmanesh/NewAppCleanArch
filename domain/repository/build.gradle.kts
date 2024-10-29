plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
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
}

dependencies {
    implementation(project(Dependencies.Module.domainCommonEntity))

    // Android
    implementation(Dependencies.Android.coreKts)

    // Test
    testImplementation(Dependencies.Test.junit)

    // Paging
    implementation(Dependencies.ThirdParty.pagingCommon)

}

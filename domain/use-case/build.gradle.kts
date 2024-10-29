plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kaptPlugin)
    id(Dependencies.Plugins.hilt)
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = AppConfig.NameSpace.usecase

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
    implementation(project(Dependencies.Module.domainRepository))
    implementation(project(Dependencies.Module.dataRepositoryImpl))

    // Android
    implementation(Dependencies.Android.coreKts)

    implementation(Dependencies.ThirdParty.pagingCommon)

    // Hilt
    implementation(Dependencies.ThirdParty.hiltAndroid)
    kapt(Dependencies.ThirdParty.hiltKapt)

}

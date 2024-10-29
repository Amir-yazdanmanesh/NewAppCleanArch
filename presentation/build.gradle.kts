plugins {
    id(Dependencies.Plugins.application)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kaptPlugin)
    id(Dependencies.Plugins.hilt)
    id(Dependencies.Plugins.parcelize)
    id(Dependencies.Plugins.composePlugin)
    id(Dependencies.Plugins.serialization)
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = AppConfig.NameSpace.yazdanmanesh

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = AppConfig.javaCompatibility
        targetCompatibility = AppConfig.javaCompatibility
    }

    kotlinOptions {
        jvmTarget = AppConfig.javaJvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Android.Version.compose
    }
}

dependencies {
    implementation(project(Dependencies.Module.domainUseCase))
    implementation(project(Dependencies.Module.domainCommonEntity))
    implementation(project(Dependencies.Module.domainRepository))

    // Android
    implementation(Dependencies.Android.coreKts)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.ThirdParty.material3)
    implementation(Dependencies.ThirdParty.androidMaterial)


    // Hilt
    implementation(Dependencies.ThirdParty.hiltAndroid)
    implementation(Dependencies.ThirdParty.hiltNavigationCompose)
    kapt(Dependencies.ThirdParty.hiltKapt)

    // Retrofit
    implementation(Dependencies.ThirdParty.retrofit)
    implementation(Dependencies.ThirdParty.retrofitConverterGson)
    implementation(Dependencies.ThirdParty.loggingInterceptor)

    // Paging
    implementation(Dependencies.ThirdParty.paging)
    implementation(Dependencies.ThirdParty.pagingCompose)


    // Coroutines
    implementation(Dependencies.ThirdParty.kotlinxCoroutinesCore)
    implementation(Dependencies.ThirdParty.kotlinxCoroutinesAndroid)

    // Room
    implementation(Dependencies.ThirdParty.roomRuntime)
    implementation(Dependencies.ThirdParty.roomKtx)
    kapt(Dependencies.ThirdParty.roomCompiler)

    // Compose
    implementation(Dependencies.Android.activityCompose)
    implementation(Dependencies.Android.navigationCompose)
    implementation(Dependencies.Android.composeUi)
    implementation(Dependencies.Android.composeMaterial)
    implementation(Dependencies.Android.composeMaterialIconsExtended)
    implementation(Dependencies.Android.composeUiTooling)
    implementation(Dependencies.Android.composeUiToolingPreview)

    // Lifecycle
    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation(Dependencies.Android.lifecycleViewModelKtx)
    implementation(Dependencies.Android.lifecycleViewModelCompose)

    // Third Party
    implementation(Dependencies.ThirdParty.coilCompose)
    implementation(Dependencies.ThirdParty.coilNetwork)
    implementation(Dependencies.ThirdParty.collectionsImmutable)
    implementation(Dependencies.ThirdParty.serializationJson)

    // Test
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.kotlinxCoroutinesTest)
    testImplementation(Dependencies.Test.kotlinTest)

    debugImplementation(Dependencies.AndroidTest.composeManifest)

    androidTestImplementation(Dependencies.AndroidTest.composeJunit)
    androidTestImplementation(Dependencies.AndroidTest.junit)
}

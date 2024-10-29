object Dependencies {

    object Plugins {
        const val application = "com.android.application"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val hilt = "dagger.hilt.android.plugin"
        const val kaptPlugin = "kotlin-kapt"
        const val androidLibrary = "com.android.library"
        const val parcelize = "kotlin-parcelize"
        const val serialization = "org.jetbrains.kotlin.plugin.serialization"
        const val composePlugin = "org.jetbrains.kotlin.plugin.compose"
    }

    object ClassPath {

        object Version {
            const val gradle = "8.4.1"
            const val kotlin = "2.0.0"
            const val hilt = "2.48.1"
            const val serialization = "2.0.21"
            const val compilerPlugin = "2.0.0"
        }

        const val gradle = "com.android.tools.build:gradle:${Version.gradle}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:${Version.serialization}"
        const val compilerPlugin = "org.jetbrains.kotlin:compose-compiler-gradle-plugin:${Version.compilerPlugin}"
    }

    object Android {

        object Version {
            const val coreKtx = "1.12.0"
            const val appCompat = "1.6.1"
            const val activityCompose = "1.7.2"
            const val compose = "1.5.15"
            const val material = "1.7.4"
            const val composeNavigation = "2.9.0-alpha01"
            const val lifecycle = "2.6.2"
        }

        const val coreKts = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"

        // Compose
        const val composeUi = "androidx.compose.ui:ui:${Version.compose}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Version.material}"
        const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Version.material}"

        // Lifecycle
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"

        // Navigation
        const val navigationCompose = "androidx.navigation:navigation-compose:${Version.composeNavigation}"
        const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.lifecycle}"
    }

    object ThirdParty {

        object Version {
            const val androidMaterial = "1.10.0"
            const val coilCompose = "3.0.0-rc01"
            const val material3 = "1.1.2"
            const val coroutines = "1.7.3"
            const val hilt = "2.48.1"
            const val room = "2.6.1"
            const val hiltNavigationCompose = "1.1.0"
            const val collectionsImmutable = "0.3.6"
            const val retrofit = "2.11.0"
            const val loggingInterceptor = "4.11.0"
            const val paging_version = "3.2.1"
            const val annotation = "1.6.0"
            const val serializationJson = "1.7.3"
        }

        // Coroutines
        const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        const val androidMaterial = "com.google.android.material:material:${Version.androidMaterial}"
        const val material3 = "androidx.compose.material3:material3:${Version.material3}"
        const val coilCompose = "io.coil-kt.coil3:coil-compose:${Version.coilCompose}"
        const val coilNetwork = "io.coil-kt.coil3:coil-network-okhttp:${Version.coilCompose}"
        const val collectionsImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Version.collectionsImmutable}"
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.serializationJson}"

        // Hilt
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationCompose}"
        const val hiltKapt = "com.google.dagger:hilt-android-compiler:${Version.hilt}"

        // Room
        const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
        const val roomKtx = "androidx.room:room-ktx:${Version.room}"
        const val roomPaging = "androidx.room:room-paging:${Version.room}"

        // Retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}"

        // Paging
        const val paging= "androidx.paging:paging-runtime:${Version.paging_version}"
        const val pagingCommon= "androidx.paging:paging-common:${Version.paging_version}"
        const val pagingCompose = "androidx.paging:paging-compose:${Version.paging_version}"

    }

    object Test {

        object Version {
            const val junit = "4.13.2"
            const val mockk = "1.13.8"
            const val okHttp3MockWebServer = "4.11.0"
        }

        const val junit = "junit:junit:${Version.junit}"
        const val mockk = "io.mockk:mockk:${Version.mockk}"
        const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${ThirdParty.Version.coroutines}"
        const val okHttp3MockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.okHttp3MockWebServer}"
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test"
    }

    object AndroidTest {
        object Version {
            const val junit = "1.1.5"
            const val junit4 = "1.7.4"
        }

        const val junit = "androidx.test.ext:junit:${Version.junit}"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Version.junit4}"
        const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Android.Version.compose}"
    }

    object Module {
        const val domain = ":domain"
        const val data = ":data"
        const val domainCommonEntity = ":domain:common-entity"
        const val domainRepository = ":domain:repository"
        const val domainUseCase = ":domain:use-case"
        const val dataNetwork = ":data:network"
        const val dataRepositoryImpl = ":data:repository-impl"
        const val dataCache = ":data:cache"
    }
}

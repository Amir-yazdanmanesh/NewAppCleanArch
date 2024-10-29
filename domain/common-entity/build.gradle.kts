plugins {
    id(Dependencies.Plugins.androidLibrary)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.serialization)
}

android {
    namespace = AppConfig.NameSpace.commonEntity
    compileSdk = AppConfig.compileSdk

    compileOptions {
        sourceCompatibility = AppConfig.javaCompatibility
        targetCompatibility = AppConfig.javaCompatibility
    }
}


dependencies {
    implementation(Dependencies.ThirdParty.serializationJson)
}
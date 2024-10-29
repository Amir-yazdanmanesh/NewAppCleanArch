import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSdk = 35
    const val minSdk = 23
    const val targetSdk = 35
    const val applicationId = "com.example.yazdanmanesh"
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"

    val javaJvmTarget = JavaVersion.VERSION_17.toString()
    val javaCompatibility = JavaVersion.VERSION_17

    object NameSpace {
        const val data = "com.example.data"
        const val domain = "com.example.domain"
        const val yazdanmanesh = "com.example.yazdanmanesh"
        const val commonEntity = "com.example.yazdanmanesh.common_entity"
        const val repository = "com.example.yazdanmanesh.repository"
        const val usecase = "com.example.yazdanmanesh.usecase"
        const val cache = "com.example.yazdanmanesh.cache"
        const val network = "com.example.yazdanmanesh.network"
    }
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.ClassPath.gradle)
        classpath(Dependencies.ClassPath.kotlinGradlePlugin)
        classpath(Dependencies.ClassPath.hiltGradlePlugin)
        classpath(Dependencies.ClassPath.serializationPlugin)
        classpath(Dependencies.ClassPath.compilerPlugin)
    }
}


tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
tasks.register("testClasses")

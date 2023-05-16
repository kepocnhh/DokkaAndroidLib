import java.net.URL

version = "0.0.1"

repositories {
    google()
    mavenCentral()
}

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.dokka") version Version.dokka
}

android {
    namespace = "dokka.android.lib"
    compileSdk = Version.Android.compileSdk

    defaultConfig {
        minSdk = Version.Android.minSdk
    }

    libraryVariants.all {
        val variant = this
        val output = outputs.single()
        check(output is com.android.build.gradle.internal.api.LibraryVariantOutputImpl)
        output.outputFileName = listOf(
            rootProject.name,
            name,
            version
        ).joinToString(separator = "-", postfix = ".aar")
        task<org.jetbrains.dokka.gradle.DokkaTask>("assemble${variant.name.capitalize()}Documentation") {
            outputDirectory.set(buildDir.resolve("documentation/${variant.name}"))
            moduleName.set(rootProject.name)
            moduleVersion.set(version.toString())
            dokkaSourceSets.create("${variant.name}Main") {
                val path = "src/main/kotlin"
                reportUndocumented.set(false)
                sourceLink {
                    localDirectory.set(file(path))
                    val url = "https://github.com/kepocnhh/DokkaAndroidLib"
                    remoteUrl.set(URL("$url/tree/${moduleVersion.get()}/lib/$path"))
                }
                jdkVersion.set(Version.jvmTarget.toInt())
            }
        }
        afterEvaluate {
            tasks.getByName<JavaCompile>("compile${variant.name.capitalize()}JavaWithJavac") {
                targetCompatibility = Version.jvmTarget
            }
            tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compile${variant.name.capitalize()}Kotlin") {
                kotlinOptions.jvmTarget = Version.jvmTarget
            }
        }
    }
}

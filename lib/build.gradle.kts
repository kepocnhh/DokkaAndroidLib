import com.android.build.gradle.api.BaseVariant
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

/*
fun BaseVariant.assembleDocumentation() {
    val variant = this
    task<org.jetbrains.dokka.gradle.DokkaTask>(camelCase("assemble", variant.name, "Documentation")) {
        outputDirectory.set(buildDir.resolve("documentation/${variant.name}"))
        moduleName.set(Repository.name)
        moduleVersion.set(getVersion())
        dokkaSourceSets.create(camelCase(variant.name, "main")) {
            reportUndocumented.set(false)
            sourceLink {
                val path = "src/main/kotlin"
                localDirectory.set(file(path))
                val url = GitHubUtil.url(Repository.owner, Repository.name)
                remoteUrl.set(URL("$url/tree/${moduleVersion.get()}/lib/$path"))
            }
            jdkVersion.set(Version.jvmTarget.toInt())
        }
    }
}
*/

android {
    namespace = "dokka.android.lib"
    compileSdk = Version.Android.compileSdk

    defaultConfig {
        minSdk = Version.Android.minSdk
    }

/*
    libraryVariants.all {
        val variant = this
        val output = variant.outputs.single()
        check(output is com.android.build.gradle.internal.api.LibraryVariantOutputImpl)
        output.outputFileName = getOutputFileName("aar")
        assembleDocumentation()
        afterEvaluate {
            tasks.getByName<JavaCompile>(camelCase("compile", variant.name, "JavaWithJavac")) {
                targetCompatibility = Version.jvmTarget
            }
            tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>(camelCase("compile", variant.name, "Kotlin")) {
                kotlinOptions.jvmTarget = Version.jvmTarget
            }
        }
    }
*/
}

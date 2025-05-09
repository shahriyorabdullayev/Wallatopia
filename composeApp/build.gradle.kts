import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.androidx.core)
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.android)
            implementation(libs.androidx.core.splashscreen)
            implementation (libs.androidx.preference.ktx)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            //koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose)

            //viewModel
            implementation(libs.lifecycle.viewmodel)
            //navigation
            implementation(libs.navigation.compose)

            //ktor
            implementation(libs.bundles.ktor)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.kotlin.serialization)
            implementation(libs.ktor.client.logging)

            //logger
            implementation(libs.napier)

            //coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.coroutines)

            //Paging
            implementation(libs.paging.compose.common)

            implementation(libs.uri.kmp)

            implementation(libs.cmptoast)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

    sourceSets.commonMain {
        kotlin.srcDir("build/generated/ksp/metadata")
    }
}

android {
    namespace = "uz.droid.wallatopia"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        val pixabayApiKey = project.loadSecretProperty(propertyName = "pixabay_api_key")
        buildConfigField("String", "pixabayApiKey", "\"$pixabayApiKey\"")
        applicationId = "uz.droid.wallatopia"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 2
        versionName = "1.1"
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core)
    debugImplementation(compose.uiTooling)
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}


fun Project.loadSecretProperty(
    propertyName: String,
): String {
    val secretProperties = Properties()
    val secretPropertiesFile = project.rootProject.file("secret.properties")
    if (secretPropertiesFile.exists()) {
        secretProperties.load(secretPropertiesFile.inputStream())
        return secretProperties.getProperty(propertyName)
    } else {
        throw GradleException("can not find property : $propertyName")
    }

}

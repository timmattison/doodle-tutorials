plugins {
    kotlin("multiplatform"       )
    kotlin("plugin.serialization")
}

kotlin {
    jsTargets ()
    jvmTargets()

    val mockkVersion        : String by project
    val doodleVersion       : String by project
    val coroutinesVersion   : String by project
    val serializationVersion: String by project

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

                api("io.nacular.doodle:core:$doodleVersion"    )
                api("io.nacular.doodle:browser:$doodleVersion" )
                api("io.nacular.doodle:controls:$doodleVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("io.mockk:mockk:$mockkVersion")
            }
        }

        js().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
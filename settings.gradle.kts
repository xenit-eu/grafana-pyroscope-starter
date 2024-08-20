pluginManagement {
    plugins {
        id("org.ajoberstar.grgit") version "5.2.2"
        id("org.sonarqube") version "5.0.0.4638"
    }
}

plugins {
    id("org.ajoberstar.reckon.settings") version "0.18.3"
    id("eu.xenit.enterprise-conventions.oss") version "0.5.1"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

extensions.configure<org.ajoberstar.reckon.gradle.ReckonExtension> {
    setDefaultInferredScope("patch")
    snapshots()
    setScopeCalc(calcScopeFromCommitMessages())
    setStageCalc { _: Any, _: Any -> java.util.Optional.empty() }
}

rootProject.name = "grafana-pyroscope-starter"

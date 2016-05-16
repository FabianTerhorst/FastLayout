package io.fabianterhorst.fastlayout.gradle

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.neenbedankt.gradle.androidapt.AndroidAptPlugin
//import io.fabianterhorst.fastlayout.transformer.FastLayoutTransformer
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class FastLayout implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // Make sure the project is either an Android application or library
        def isAndroidApp = project.plugins.withType(AppPlugin)
        def isAndroidLib = project.plugins.withType(LibraryPlugin)
        if (!isAndroidApp && !isAndroidLib) {
            throw new GradleException("'com.android.application' or 'com.android.library' plugin required.")
        }

        if (!isTransformAvailable()) {
            throw new GradleException('FastLayout gradle plugin only supports android gradle plugin 1.5.0 or later.')
        }

        def isKotlinProject = project.plugins.find {
            it.getClass().name == 'org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper'
        }

        if (!isKotlinProject) {
            project.plugins.apply(AndroidAptPlugin)
        }

        //project.android.registerTransform(new FastLayoutTransformer(project))
        project.repositories.add(project.getRepositories().jcenter())
        project.dependencies.add("compile", "io.fabianterhorst:fastlayout:${Version.VERSION}")
        project.dependencies.add("compile", "io.fabianterhorst:fastlayout-annotations:${Version.VERSION}")
        if (isKotlinProject) {
            project.dependencies.add("kapt", "io.fabianterhorst:fastlayout-annotations:${Version.VERSION}")
            project.dependencies.add("kapt", "io.fabianterhorst:fastlayout-processor:${Version.VERSION}")
        } else {
            project.dependencies.add("apt", "io.fabianterhorst:fastlayout-annotations:${Version.VERSION}")
            project.dependencies.add("apt", "io.fabianterhorst:fastlayout-processor:${Version.VERSION}")
        }
    }

    private static boolean isTransformAvailable() {
        try {
            Class.forName('com.android.build.api.transform.Transform')
            return true
        } catch (Exception ignored) {
            return false
        }
    }
}
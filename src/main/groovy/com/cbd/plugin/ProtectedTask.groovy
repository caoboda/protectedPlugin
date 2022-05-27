package com.cbd.plugin

import com.android.builder.model.SigningConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

class ProtectedTask extends DefaultTask {

    Protected pro
    SigningConfig signingConfig
    File apk

    ProtectedTask() {
        group = "jiagu"
    }

    @TaskAction
    def run() {
        //调用命令行工具
        project.exec {
            // java -jar jiagu.jar -login user password
            it.commandLine("java", "-jar", pro.protectedTools, "-login", pro.userName, pro.password)
        }

        if (signingConfig) {
            project.exec {
                // java -jar jiagu.jar -importsign  xxxx
                it.commandLine("java", "-jar", pro.protectedTools, "-importsign", signingConfig.storeFile.absolutePath,
                        signingConfig.storePassword, signingConfig.keyAlias, signingConfig.keyPassword)
            }
        }
        project.exec {
            // java -jar jiagu.jar -jiagu  xxxx
            it.commandLine("java", "-jar", pro.protectedTools, "-jiagu", apk.absolutePath,
                    apk.parent, "-autosign")
        }


    }
}

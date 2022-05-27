package com.cbd.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.builder.model.SigningConfig
import org.gradle.api.Plugin;
import org.gradle.api.Project;

class ProtectedPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        Protected pro= project.extensions.create("jiagu",Protected)
        //回调, 在gradle配置完成之后回调，在解析完build.gradle之后回调
        project.afterEvaluate {
            AppExtension android = project.extensions.android

            android.applicationVariants.all { ApplicationVariant variant->
                    //对应变体(debug/release)的签名配置
                   SigningConfig signingConfig= variant.signingConfig
                   variant.outputs.all { BaseVariantOutput variantOutput ->
                       //输出的apk文件
                       File apk = variantOutput.outputFile
                       //创建加固任务
                       ProtectedTask protectedTask =project.tasks.create("jiagu${variantOutput.baseName.capitalize()}",ProtectedTask)
                       protectedTask.pro=pro
                       protectedTask.signingConfig=signingConfig
                       protectedTask.apk=apk
                   }

            }
        }

    }
}

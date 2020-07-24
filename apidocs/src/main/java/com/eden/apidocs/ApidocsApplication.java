package com.eden.apidocs;

import io.github.yedaxia.apidocs.Docs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApidocsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApidocsApplication.class, args);
        generatorDoc();
    }

    public static void generatorDoc() {
        Docs.DocsConfig config = new Docs.DocsConfig();
        config.setProjectPath("/home/eden/IdeaProjects/daydayup/apidocs"); // 项目根目录
//        config.setProjectName("ProjectName"); // 项目名称
//        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("/home/eden/IdeaProjects/daydayup/apidocs/doc"); // 生成API 文档所在目录
        config.setMvcFramework("spring");
        config.addJavaSrcPath("/home/eden/IdeaProjects/daydayup/apidocs/src");
//        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}

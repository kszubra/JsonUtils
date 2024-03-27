package org.example.generateclass;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonToClassConverter {

    public static void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName)
            throws IOException {
        JCodeModel jcodeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }
        };

        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

        jcodeModel.build(outputJavaClassDirectory);
    }

    public static void main(String[] args) throws IOException {
        String jsonAbsoluteFilePath = "";
        URL jsonUrl = new URL("file:///" + jsonAbsoluteFilePath);
        File destinationCatalog = new File("");
        String packageName ="result";
        String newClassName = "Result";
        JsonToClassConverter.convertJsonToJavaClass(
                jsonUrl,
                destinationCatalog,
                packageName,
                newClassName
        );
    }
}

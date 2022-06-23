package com.fedag.recruitmentSystem.service.utils;

import static com.github.javaparser.ast.Modifier.Keyword.PUBLIC;
import static com.github.javaparser.ast.Modifier.Keyword.STATIC;
import com.fedag.recruitmentSystem.config.CompilerConfig;
import com.fedag.recruitmentSystem.model.CompilerRequest;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.PrimitiveType.Primitive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author koilng
 * @created 20/06/2022 - 12:42
 */
public class CompilerApi {

  public static ResponseEntity<String> execute(CompilerRequest compilerRequest) {
    compilerRequest.setScript(wrapCode(compilerRequest.getScript()));
    return new RestTemplate().postForEntity(CompilerConfig.COMPILER_URL, setCredentials(compilerRequest), String.class);
  }

  private static CompilerRequest setCredentials(CompilerRequest compilerRequest) {
    compilerRequest.setClientId(CompilerConfig.CLIENT_ID);
    compilerRequest.setClientSecret(CompilerConfig.CLIENT_SECRET);
    return compilerRequest;
  }

  private static String wrapCode(String code) {

    //Parsing code from string
    CompilationUnit cu = StaticJavaParser.parse(code);

    //Getting class from parsed string
    ClassOrInterfaceDeclaration myClass = cu.getClassByName("task").get();

    //Variable declaration
    VariableDeclarator firstInput = new VariableDeclarator();
    firstInput.setName("x");
    firstInput.setType(new PrimitiveType(Primitive.INT));
    firstInput.setInitializer("25");

    VariableDeclarator secondInput = new VariableDeclarator();
    secondInput.setName("y");
    secondInput.setType(new PrimitiveType(Primitive.INT));
    secondInput.setInitializer("30");

    //Setting up method input params
    NodeList<Expression> methodParameters = new NodeList<>();

    //adding params
    methodParameters.add(firstInput.getInitializer().get());
    methodParameters.add(secondInput.getInitializer().get());

    //Creating method call solve(*parameters*)
    MethodCallExpr methodCall = new MethodCallExpr();
    methodCall
        .setName("solve").setArguments(methodParameters);

    //generating main method
    generateMain(myClass);

    //Inserting method call in main method body
    myClass.getMethodsByName("main").get(0).setBody(new BlockStmt()
        .addStatement("System.out.println(" +methodCall + ");"));

    return myClass.toString();
  }

  private static void generateMain(ClassOrInterfaceDeclaration declaration) {
    NodeList<Parameter> parameters = new NodeList<>();

    parameters.add(new Parameter()
        .setType(String.class)
        .setVarArgs(true)
        .setName("args"));

    declaration
        .addMethod("main", PUBLIC, STATIC)
        .setParameters(parameters);
  }
}

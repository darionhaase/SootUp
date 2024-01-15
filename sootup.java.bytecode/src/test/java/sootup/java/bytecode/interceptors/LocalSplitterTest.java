package sootup.java.bytecode.interceptors;

import categories.Java8Test;
import com.sun.jdi.IntegerType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import sootup.core.jimple.basic.Local;
import sootup.core.jimple.basic.NoPositionInformation;
import sootup.core.model.Body;
import sootup.core.model.SootMethod;
import sootup.core.signatures.MethodSignature;
import sootup.core.signatures.MethodSubSignature;
import sootup.core.signatures.PackageName;
import sootup.core.types.ClassType;
import sootup.core.types.PrimitiveType;
import sootup.core.types.UnknownType;
import sootup.core.types.VoidType;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.types.JavaClassType;
import sootup.java.core.views.JavaView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category(Java8Test.class)
public class LocalSplitterTest {
    JavaView view;

    @Before
    public void Setup() {
        String classPath = "src/test/java/resources/interceptors";
        JavaClassPathAnalysisInputLocation inputLocation = new JavaClassPathAnalysisInputLocation(classPath);
        view = new JavaView(inputLocation);
    }

    @Test
    public void testSimpleAssignment() {
        ClassType type = new JavaClassType("LocalSplitterTarget", PackageName.DEFAULT_PACKAGE);
        MethodSignature sig = new MethodSignature(type, new MethodSubSignature("case0", Collections.EMPTY_LIST, VoidType.getInstance()));
        SootMethod sootMethod = view.getMethod(sig).get();
        Body originalBody = sootMethod.getBody();

        List<Local> expectedLocals = new ArrayList<>();
        expectedLocals.addAll(originalBody.getLocals());
        expectedLocals.add(new Local("$l1#1", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l2#2", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#3", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l2#4", UnknownType.getInstance(), NoPositionInformation.getInstance()));

        Body.BodyBuilder builder = Body.builder(originalBody, Collections.emptySet());
        LocalSplitter localSplitter = new LocalSplitter();
        localSplitter.interceptBody(builder, view);

        Body newBody = builder.build();
        assertTrue(expectedLocals.containsAll(newBody.getLocals()));
        assertTrue(newBody.getLocals().containsAll(expectedLocals));

        String expectedStmts = "$l0 := @this: LocalSplitterTarget;\n" +
                "$l1#1 = 0;\n" +
                "$l2#2 = 1;\n" +
                "$l1#3 = $l2#2 + 1;\n" +
                "$l2#4 = $l1#3 + 1;\n" +
                "\n" +
                "return;";

        assertEquals(expectedStmts, newBody.getStmtGraph().toString().trim());
    }

    @Test
    public void testSelfAssignment() {
        ClassType type = new JavaClassType("LocalSplitterTarget", PackageName.DEFAULT_PACKAGE);
        MethodSignature sig = new MethodSignature(type, new MethodSubSignature("case1", Collections.EMPTY_LIST, VoidType.getInstance()));
        SootMethod sootMethod = view.getMethod(sig).get();
        Body originalBody = sootMethod.getBody();

        List<Local> expectedLocals = new ArrayList<>();
        expectedLocals.addAll(originalBody.getLocals());
        expectedLocals.add(new Local("$l1#1", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l2#2", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#3", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l2#4", UnknownType.getInstance(), NoPositionInformation.getInstance()));

        Body.BodyBuilder builder = Body.builder(originalBody, Collections.emptySet());
        LocalSplitter localSplitter = new LocalSplitter();
        localSplitter.interceptBody(builder, view);

        Body newBody = builder.build();
        assertTrue(expectedLocals.containsAll(newBody.getLocals()));
        assertTrue(newBody.getLocals().containsAll(expectedLocals));

        String expectedStmts = "$l0 := @this: LocalSplitterTarget;\n" +
                "$l1#1 = 0;\n" +
                "$l2#2 = 1;\n" +
                "$l1#3 = $l1#1 + 1;\n" +
                "$l2#4 = $l2#2 + 1;\n" +
                "\n" +
                "return;";

        assertEquals(expectedStmts, newBody.getStmtGraph().toString().trim());
    }

    @Test
    public void testBranch() {
        ClassType type = new JavaClassType("LocalSplitterTarget", PackageName.DEFAULT_PACKAGE);
        MethodSignature sig = new MethodSignature(type, new MethodSubSignature("case2", Collections.EMPTY_LIST, PrimitiveType.IntType.getInstance()));
        SootMethod sootMethod = view.getMethod(sig).get();
        Body originalBody = sootMethod.getBody();

        List<Local> expectedLocals = new ArrayList<>();
        expectedLocals.addAll(originalBody.getLocals());
        expectedLocals.add(new Local("$l1#1", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#2", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#3", UnknownType.getInstance(), NoPositionInformation.getInstance()));

        Body.BodyBuilder builder = Body.builder(originalBody, Collections.emptySet());
        LocalSplitter localSplitter = new LocalSplitter();
        localSplitter.interceptBody(builder, view);

        Body newBody = builder.build();
        assertTrue(expectedLocals.containsAll(newBody.getLocals()));
        assertTrue(newBody.getLocals().containsAll(expectedLocals));

        String expectedStmts = "$l0 := @this: LocalSplitterTarget;\n" +
                "$l1#1 = 0;\n" +
                "\n" +
                "if $l1#1 >= 0 goto label1;\n" +
                "$l1#2 = $l1#1 + 1;\n" +
                "\n" +
                "goto label2;\n" +
                "\n" +
                "label1:\n" +
                "$l1#3 = $l1#1 - 1;\n" +
                "$l1#2 = $l1#3 + 2;\n" +
                "\n" +
                "label2:\n" +
                "return $l1#2;";
        assertEquals(expectedStmts, newBody.getStmtGraph().toString().trim());
    }


    @Test
    public void testBranchMoreLocals() {
        ClassType type = new JavaClassType("LocalSplitterTarget", PackageName.DEFAULT_PACKAGE);
        MethodSignature sig = new MethodSignature(type, new MethodSubSignature("case3", Collections.EMPTY_LIST, PrimitiveType.IntType.getInstance()));
        SootMethod sootMethod = view.getMethod(sig).get();
        Body originalBody = sootMethod.getBody();

        List<Local> expectedLocals = new ArrayList<>();
        expectedLocals.addAll(originalBody.getLocals());
        expectedLocals.add(new Local("$l1#1", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#2", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#3", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#4", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#5", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#6", UnknownType.getInstance(), NoPositionInformation.getInstance()));

        Body.BodyBuilder builder = Body.builder(originalBody, Collections.emptySet());
        LocalSplitter localSplitter = new LocalSplitter();
        localSplitter.interceptBody(builder, view);

        Body newBody = builder.build();
        assertTrue(expectedLocals.containsAll(newBody.getLocals()));
        assertTrue(newBody.getLocals().containsAll(expectedLocals));

        String expectedStmts = "$l0 := @this: LocalSplitterTarget;\n" +
                "$l1#1 = 0;\n" +
                "\n" +
                "if $l1#1 >= 0 goto label1;\n" +
                "$l1#2 = $l1#1 + 1;\n" +
                "$l1#3 = $l1#2 + 2;\n" +
                "$l1#4 = $l1#3 + 3;\n" +
                "\n" +
                "goto label2;\n" +
                "\n" +
                "label1:\n" +
                "$l1#5 = $l1#1 - 1;\n" +
                "$l1#6 = $l1#5 - 2;\n" +
                "$l1#4 = $l1#6 - 3;\n" +
                "\n" +
                "label2:\n" +
                "return $l1#4;";
        assertEquals(expectedStmts, newBody.getStmtGraph().toString().trim());
    }


    @Test
    public void testBranchMoreBranches() {
        ClassType type = new JavaClassType("LocalSplitterTarget", PackageName.DEFAULT_PACKAGE);
        MethodSignature sig = new MethodSignature(type, new MethodSubSignature("case4", Collections.EMPTY_LIST, PrimitiveType.IntType.getInstance()));
        SootMethod sootMethod = view.getMethod(sig).get();
        Body originalBody = sootMethod.getBody();

        List<Local> expectedLocals = new ArrayList<>();
        expectedLocals.addAll(originalBody.getLocals());
        expectedLocals.add(new Local("$l1#1", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#2", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#3", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#4", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#5", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#6", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#7", UnknownType.getInstance(), NoPositionInformation.getInstance()));

        Body.BodyBuilder builder = Body.builder(originalBody, Collections.emptySet());
        LocalSplitter localSplitter = new LocalSplitter();
        localSplitter.interceptBody(builder, view);

        Body newBody = builder.build();
        assertTrue(expectedLocals.containsAll(newBody.getLocals()));
        assertTrue(newBody.getLocals().containsAll(expectedLocals));

        String expectedStmts =
                "$l0 := @this: LocalSplitterTarget;\n" +
                        "$l1#1 = 0;\n" +
                        "\n" +
                        "if $l1#1 >= 0 goto label1;\n" +
                        "$l1#2 = $l1#1 + 1;\n" +
                        "$l1#3 = $l1#2 + 2;\n" +
                        "\n" +
                        "goto label2;\n" +
                        "\n" +
                        "label1:\n" +
                        "$l1#4 = $l1#1 - 1;\n" +
                        "$l1#3 = $l1#4 - 2;\n" +
                        "\n" +
                        "label2:\n" +
                        "if $l1#3 <= 1 goto label3;\n" +
                        "$l1#5 = $l1#3 + 3;\n" +
                        "$l1#6 = $l1#5 + 5;\n" +
                        "\n" +
                        "goto label4;\n" +
                        "\n" +
                        "label3:\n" +
                        "$l1#7 = $l1#3 - 3;\n" +
                        "$l1#6 = $l1#7 - 5;\n" +
                        "\n" +
                        "label4:\n" +
                        "return $l1#6;";
        assertEquals(expectedStmts, newBody.getStmtGraph().toString().trim());
    }


    @Test
    public void testBranchElseIf() {
        ClassType type = new JavaClassType("LocalSplitterTarget", PackageName.DEFAULT_PACKAGE);
        MethodSignature sig = new MethodSignature(type, new MethodSubSignature("case5", Collections.EMPTY_LIST, PrimitiveType.IntType.getInstance()));
        SootMethod sootMethod = view.getMethod(sig).get();
        Body originalBody = sootMethod.getBody();

        List<Local> expectedLocals = new ArrayList<>();
        expectedLocals.addAll(originalBody.getLocals());
        expectedLocals.add(new Local("$l1#1", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#2", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#3", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#4", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#5", UnknownType.getInstance(), NoPositionInformation.getInstance()));

        Body.BodyBuilder builder = Body.builder(originalBody, Collections.emptySet());
        LocalSplitter localSplitter = new LocalSplitter();
        localSplitter.interceptBody(builder, view);

        Body newBody = builder.build();
        assertTrue(expectedLocals.containsAll(newBody.getLocals()));
        assertTrue(newBody.getLocals().containsAll(expectedLocals));

        String expectedStmts =
                "$l0 := @this: LocalSplitterTarget;\n" +
                        "$l1#1 = 0;\n" +
                        "\n" +
                        "if $l1#1 >= 0 goto label1;\n" +
                        "$l1#2 = $l1#1 + 1;\n" +
                        "$l1#3 = $l1#2 + 2;\n" +
                        "\n" +
                        "goto label3;\n" +
                        "\n" +
                        "label1:\n" +
                        "if $l1#1 >= 5 goto label2;\n" +
                        "$l1#4 = $l1#1 - 1;\n" +
                        "$l1#3 = $l1#4 - 2;\n" +
                        "\n" +
                        "goto label3;\n" +
                        "\n" +
                        "label2:\n" +
                        "$l1#5 = $l1#1 * 1;\n" +
                        "$l1#3 = $l1#5 * 2;\n" +
                        "\n" +
                        "label3:\n" +
                        "return $l1#3;";
        assertEquals(expectedStmts, newBody.getStmtGraph().toString().trim());
    }


    @Test
    public void testForLoop() {
        ClassType type = new JavaClassType("LocalSplitterTarget", PackageName.DEFAULT_PACKAGE);
        MethodSignature sig = new MethodSignature(type, new MethodSubSignature("case6", Collections.EMPTY_LIST, PrimitiveType.IntType.getInstance()));
        SootMethod sootMethod = view.getMethod(sig).get();
        Body originalBody = sootMethod.getBody();

        List<Local> expectedLocals = new ArrayList<>();
        expectedLocals.addAll(originalBody.getLocals());
        expectedLocals.add(new Local("$l1#1", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#2", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#3", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#4", UnknownType.getInstance(), NoPositionInformation.getInstance()));
        expectedLocals.add(new Local("$l1#5", UnknownType.getInstance(), NoPositionInformation.getInstance()));

        Body.BodyBuilder builder = Body.builder(originalBody, Collections.emptySet());
        LocalSplitter localSplitter = new LocalSplitter();
        localSplitter.interceptBody(builder, view);


        Body newBody = builder.build();

        System.out.println(newBody);

//        assertTrue(expectedLocals.containsAll(newBody.getLocals()));
//        assertTrue(newBody.getLocals().containsAll(expectedLocals));

        String expectedStmts =
                "$l0 := @this: LocalSplitterTarget;\n" +
                        "$l1#1 = 0;\n" +
                        "\n" +
                        "if $l1#1 >= 0 goto label1;\n" +
                        "$l1#2 = $l1#1 + 1;\n" +
                        "$l1#3 = $l1#2 + 2;\n" +
                        "\n" +
                        "goto label3;\n" +
                        "\n" +
                        "label1:\n" +
                        "if $l1#1 >= 5 goto label2;\n" +
                        "$l1#4 = $l1#1 - 1;\n" +
                        "$l1#3 = $l1#4 - 2;\n" +
                        "\n" +
                        "goto label3;\n" +
                        "\n" +
                        "label2:\n" +
                        "$l1#5 = $l1#1 * 1;\n" +
                        "$l1#3 = $l1#5 * 2;\n" +
                        "\n" +
                        "label3:\n" +
                        "return $l1#3;";
//        assertEquals(expectedStmts, newBody.getStmtGraph().toString().trim());
    }

}

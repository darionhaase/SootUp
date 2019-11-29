package de.upb.swt.soot.test.java.sourcecode.typehierarchy.testcase;

import categories.Java8Test;
import de.upb.swt.soot.callgraph.typehierarchy.TypeHierarchy;
import de.upb.swt.soot.callgraph.typehierarchy.ViewTypeHierarchy;
import de.upb.swt.soot.core.types.ClassType;
import de.upb.swt.soot.test.java.sourcecode.typehierarchy.JavaTypeHierarchyBase;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/** @author: Hasitha Rajapakse * */

@Category(Java8Test.class)
public class InheritenceTransitivityTest extends JavaTypeHierarchyBase {

  @Test
  public void method() {
    ViewTypeHierarchy typeHierarchy =
        (ViewTypeHierarchy) TypeHierarchy.fromView(customTestWatcher.getView());
    Set<ClassType> subClassSet = new HashSet<>();
    subClassSet.add(getClassType("SubClassA"));
    subClassSet.add(getClassType("SubClassB"));
    assertEquals(
        typeHierarchy.subclassesOf(getClassType(customTestWatcher.getClassName())), subClassSet);
  }
}

package de.upb.sse.sootup.jimple.parser;

import de.upb.sse.sootup.core.*;
import de.upb.sse.sootup.core.inputlocation.AnalysisInputLocation;
import de.upb.sse.sootup.core.inputlocation.ClassLoadingOptions;
import de.upb.sse.sootup.core.inputlocation.DefaultSourceTypeSpecifier;
import de.upb.sse.sootup.core.model.SootClass;
import java.util.List;
import java.util.function.Function;
import javax.annotation.Nonnull;

public class JimpleProject extends Project<SootClass<?>, JimpleView> {

  public JimpleProject(@Nonnull AnalysisInputLocation<? extends SootClass<?>> inputLocation) {
    super(JimpleLanguage.getInstance(), inputLocation, DefaultSourceTypeSpecifier.getInstance());
  }

  public JimpleProject(@Nonnull List<AnalysisInputLocation<? extends SootClass<?>>> inputLocation) {
    super(
        JimpleLanguage.getInstance(),
        inputLocation,
        JimpleLanguage.getInstance().getIdentifierFactory(),
        DefaultSourceTypeSpecifier.getInstance());
  }

  public JimpleProject(
      @Nonnull AnalysisInputLocation<? extends SootClass<?>> inputLocation,
      @Nonnull SourceTypeSpecifier sourceTypeSpecifier) {
    super(JimpleLanguage.getInstance(), inputLocation, sourceTypeSpecifier);
  }

  public JimpleProject(
      @Nonnull List<AnalysisInputLocation<? extends SootClass<?>>> inputLocations,
      @Nonnull IdentifierFactory identifierFactory,
      @Nonnull SourceTypeSpecifier sourceTypeSpecifier) {
    super(JimpleLanguage.getInstance(), inputLocations, identifierFactory, sourceTypeSpecifier);
  }

  @Nonnull
  @Override
  public JimpleView createFullView() {
    final JimpleView jimpleView = new JimpleView(this);
    jimpleView.getClasses();
    return jimpleView;
  }

  @Nonnull
  @Override
  public JimpleView createOnDemandView() {
    return new JimpleView(this);
  }

  @Nonnull
  @Override
  public JimpleView createOnDemandView(
      @Nonnull
          Function<AnalysisInputLocation<? extends SootClass<?>>, ClassLoadingOptions>
              classLoadingOptionsSpecifier) {
    return new JimpleView(this, classLoadingOptionsSpecifier);
  }
}
package io.fabianterhorst.fastlayout;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import io.fabianterhorst.fastlayout.annotations.Layout;

@SupportedAnnotationTypes("io.fabianterhorst.fastlayout.annotations.Layout")
public class LayoutProcessor extends AbstractProcessor {
    private static final String SUFFIX_PREF_WRAPPER = "Layout";

    private Configuration mFreemarkerConfiguration;

    private final List<LayoutEntity> mChilds = new ArrayList<>();

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private Configuration getFreemarkerConfiguration() {
        if (mFreemarkerConfiguration == null) {
            mFreemarkerConfiguration = new Configuration(new Version(2, 3, 22));
            mFreemarkerConfiguration.setClassForTemplateLoading(getClass(), "");
        }
        return mFreemarkerConfiguration;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String debug = "Log" + System.currentTimeMillis() + ": ";
        for (TypeElement te : annotations) {
            for (javax.lang.model.element.Element element : roundEnv.getElementsAnnotatedWith(te)) {
                TypeElement classElement = (TypeElement) element;
                PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();
                String layout = element.getAnnotation(Layout.class).value();
                LayoutEntity rootLayout = new LayoutEntity();
                try {
                    DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(layout));
                    Document document = documentBuilder.parse(is);
                    Element rootLayoutElement = document.getDocumentElement();
                    rootLayout.setId(normalizeLayoutId(rootLayoutElement.getAttribute("android:id")));
                    rootLayout.setName(rootLayoutElement.getTagName());
                    LayoutParam layoutParam = new LayoutParam();
                    layoutParam.setName(getLayoutParamsForViewGroup(rootLayout.getName()));
                    layoutParam.setWidth(rootLayoutElement.getAttribute("android:layout_width").toUpperCase());
                    layoutParam.setHeight(rootLayoutElement.getAttribute("android:layout_height").toUpperCase());
                    rootLayout.setLayoutParams(layoutParam);
                    if (rootLayoutElement.hasChildNodes()) {
                        createChildList(rootLayoutElement);
                        rootLayout.addChildren(mChilds);
                    }
                } catch (Exception ignore) {
                    debug += " exception:" + ignore.getMessage();
                }

                JavaFileObject javaFileObject;
                try {
                    Map<String, Object> args = new HashMap<>();
                    //Layout Wrapper
                    javaFileObject = processingEnv.getFiler().createSourceFile(classElement.getQualifiedName() + SUFFIX_PREF_WRAPPER);
                    Template template = getFreemarkerConfiguration().getTemplate("layoutwrapper.ftl");
                    args.put("package", packageElement.getQualifiedName());
                    args.put("keyWrapperClassName", classElement.getSimpleName() + SUFFIX_PREF_WRAPPER);
                    args.put("rootLayout", rootLayout);
                    args.put("log", debug);
                    Writer writer = javaFileObject.openWriter();
                    template.process(args, writer);
                    IOUtils.closeQuietly(writer);

                } catch (Exception e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "En error occurred while generating Prefs code " + e.getClass() + e.getMessage(), element);
                    e.printStackTrace();
                    // Problem detected: halt
                    return true;
                }
            }
        }

        /*try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);

            Iterable<? extends File> locations = fm.getLocation(StandardLocation.SOURCE_PATH);
            for(File file : locations){
                System.out.print(file.getAbsolutePath());
            }
            //FileInputStream fileInputStream = new FileInputStream("");
        }catch(Exception ignore){
        }*/

        return true;
    }

    private String getLayoutParamsForViewGroup(String viewGroup) {
        switch (viewGroup) {
            case "TextView":
                return "ViewGroup.LayoutParams";
            default:
                return viewGroup + ".LayoutParams";
        }
    }

    private String normalizeLayoutId(String layoutId) {
        return layoutId.replace("@+id/", "").replace("@id/", "");
    }

    private String getIdByNode(Node node) {
        return normalizeLayoutId(node.getAttributes().getNamedItem("android:id").getNodeValue());
    }

    private List<LayoutEntity> createChildList(Node node) {
        List<LayoutEntity> layouts = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            if (child.getAttributes() != null && child.getAttributes().getLength() > 0) {
                LayoutEntity layout = createLayoutFromChild(child);
                mChilds.add(layout);
                layouts.add(layout);
                if (child.hasChildNodes()) {
                    createChildList(child);
                }
            }
        }
        return layouts;
    }

    private LayoutEntity createLayoutFromChild(Node node) {
        LayoutEntity layout = new LayoutEntity();
        layout.setId(getIdByNode(node));
        layout.setName(node.getNodeName());
        layout.setHasChildren(node.hasChildNodes());
        LayoutParam layoutParams = new LayoutParam();
        layoutParams.setName(getLayoutParamsForViewGroup(node.getNodeName()));
        layoutParams.setWidth(node.getAttributes().getNamedItem("android:layout_width").getNodeValue().toUpperCase());
        layoutParams.setHeight(node.getAttributes().getNamedItem("android:layout_height").getNodeValue().toUpperCase());
        layout.setLayoutParams(layoutParams);
        return layout;
    }
}
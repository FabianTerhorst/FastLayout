package ${package};

import android.content.Context;
import android.util.AttributeSet;
//Todo : find full class name
import android.view.*;
import android.widget.*;
import android.util.TypedValue;
import android.content.res.Resources;

import io.fabianterhorst.fastlayout.ILayout;
import io.fabianterhorst.fastlayout.LayoutUtils;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ${keyWrapperClassName} extends ${rootLayout.name} implements ILayout, Cloneable {

    <#list rootLayout.children as child>
    private ${child.name} ${child.id};

    </#list>
    public ${keyWrapperClassName}(Context context) {
        super(context);
        init();
    }

    public ${keyWrapperClassName}(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ${keyWrapperClassName}(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ${keyWrapperClassName}(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ${rootLayout.layoutParams.name} ${rootLayout.id}LayoutParams = new ${rootLayout.layoutParams.name}(<#list rootLayout.attributes as attribute><#if attribute.type == "PARAM_CONSTRUCTOR_1">${attribute.value},</#if></#list><#list rootLayout.attributes as attribute><#if attribute.type == "PARAM_CONSTRUCTOR_2">${attribute.value}</#if></#list><#list rootLayout.attributes as attribute><#if attribute.type == "PARAM_CONSTRUCTOR_3">,${attribute.value}</#if></#list>);
        <#list rootLayout.attributes as attribute>
        <#if attribute.type == "PARAM" || attribute.type == "LAYOUT">
        <#if attribute.type == "PARAM">${rootLayout.id}LayoutParams.<#elseif attribute.type == "LAYOUT"></#if>${attribute.value};
        </#if>
        </#list>
        <#list rootLayout.layoutParamsList?keys as key>
        set${key}(<#if !rootLayout.layoutParamsList[key].value?is_number && !rootLayout.layoutParamsList[key].number>"</#if>${rootLayout.layoutParamsList[key].value}<#if !rootLayout.layoutParamsList[key].value?is_number && !rootLayout.layoutParamsList[key].number>"</#if>);
        </#list>
        this.setLayoutParams(${rootLayout.id}LayoutParams);
        <#assign parent = "this">
        <#list rootLayout.children as child>
        ${child.id} = new ${child.name}(getContext());
        ${child.layoutParams.name} ${child.id}LayoutParams = new ${child.layoutParams.name}(<#list child.attributes as attribute><#if attribute.type == "PARAM_CONSTRUCTOR_1">${attribute.value},</#if></#list><#list child.attributes as attribute><#if attribute.type == "PARAM_CONSTRUCTOR_2">${attribute.value}</#if></#list><#list child.attributes as attribute><#if attribute.type == "PARAM_CONSTRUCTOR_3">,${attribute.value}</#if></#list>);
        <#list child.layoutParamsList?keys as key>
        <#if child.layoutParamsList[key].paramValue>${child.id}LayoutParams<#else>${child.id}</#if>.<#if child.layoutParamsList[key].rule>addRule(${key}<#if child.layoutParamsList[key].value != "true">,${child.layoutParamsList[key].value}</#if>)<#elseif !child.layoutParamsList[key].setter>set${key}(<#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if>${child.layoutParamsList[key].value}<#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if>)<#else>${key} = <#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if>${child.layoutParamsList[key].value}<#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if></#if>;
        </#list>
        <#list child.attributes as attribute>
        <#if attribute.type == "PARAM" || attribute.type == "LAYOUT">
        <#if attribute.type == "PARAM">${child.id}LayoutParams<#elseif attribute.type == "LAYOUT">${child.id}</#if>.${attribute.value};
        </#if>
        </#list>
        ${child.id}.setLayoutParams(${child.id}LayoutParams);
        <#if child.hasChildren>
        <#assign parent = child.id>
        <#else>
        ${parent}.addView(${child.id});
        <#if parent != "this">
        this.addView(${parent});
        </#if>
        </#if>
        </#list>
    }

    <#list rootLayout.children as child>
    public ${child.name} get${child.id?capitalize}() {
        return ${child.id};
    }

    </#list>
    @Override
    public ILayout clone() {
        try {
            return (${keyWrapperClassName}) super.clone();
        } catch (CloneNotSupportedException ignore) {
            return new ${keyWrapperClassName}(getContext());
        }
    }
}

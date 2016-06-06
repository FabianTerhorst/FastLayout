package ${package};

import android.content.Context;
import android.util.AttributeSet;

import android.view.*;
import android.widget.*;
import android.util.TypedValue;

import io.fabianterhorst.fastlayout.ILayout;
import io.fabianterhorst.fastlayout.LayoutUtils;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ${keyWrapperClassName} extends ${rootLayout.name} implements ILayout, Cloneable {

    <#list rootLayout.children as child>
    private ${child.name} ${child.id};

    </#list>
    public ${keyWrapperClassName}(Context context) {
        super(<#list rootLayout.attributes as attribute><#if attribute.isLayoutConstructor()>${attribute.value?replace("getContext()", "context")}<#if !attribute.last>,</#if></#if></#list>);
        init();
    }

    public ${keyWrapperClassName}(Context context, AttributeSet attrs) {
        super(<#list rootLayout.attributes as attribute><#if attribute.type == "LAYOUT_CONSTRUCTOR_1">${attribute.value?replace("getContext()", "context")}</#if></#list>, attrs<#list rootLayout.attributes as attribute><#if attribute.type == "LAYOUT_CONSTRUCTOR_3">, ${attribute.value}</#if></#list>);
        init();
    }

    public ${keyWrapperClassName}(Context context, AttributeSet attrs, int defStyleAttr) {
        super(<#list rootLayout.attributes as attribute><#if attribute.type == "LAYOUT_CONSTRUCTOR_1">${attribute.value?replace("getContext()", "context")}</#if></#list>, attrs<#list rootLayout.attributes as attribute><#if attribute.type == "LAYOUT_CONSTRUCTOR_3">, ${attribute.value}</#if></#list>);
        init();
    }

    public ${keyWrapperClassName}(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(<#list rootLayout.attributes as attribute><#if attribute.type == "LAYOUT_CONSTRUCTOR_1">${attribute.value?replace("getContext()", "context")}</#if></#list>, attrs<#list rootLayout.attributes as attribute><#if attribute.type == "LAYOUT_CONSTRUCTOR_3">, ${attribute.value}</#if></#list>, defStyleRes);
        init();
    }

    private void init() {
        ${rootLayout.layoutParamsName} ${rootLayout.id}LayoutParams = new ${rootLayout.layoutParamsName}(<#list rootLayout.attributes as attribute><#if attribute.isParamsConstructor()>${attribute.value}<#if !attribute.last>,</#if></#if></#list>);
        <#list rootLayout.attributes as attribute>
        <#if attribute.type == "PARAM" || attribute.type == "LAYOUT">
        <#if attribute.type == "PARAM">${rootLayout.id}LayoutParams.<#elseif attribute.type == "LAYOUT"></#if>${attribute.value};
        </#if>
        </#list>
        this.setLayoutParams(${rootLayout.id}LayoutParams);
        <#assign parent = "this">
        <#list rootLayout.children as child>
        ${child.id} = new ${child.name}(<#list child.attributes as attribute><#if attribute.isLayoutConstructor()>${attribute.value}<#if !attribute.last>,</#if></#if></#list>);
        ${child.layoutParamsName} ${child.id}LayoutParams = new ${child.layoutParamsName}(<#list child.attributes as attribute><#if attribute.isParamsConstructor()>${attribute.value}<#if !attribute.last>,</#if></#if></#list>);
        <#list child.attributes as attribute>
        <#if attribute.type == "PARAM" || attribute.type == "LAYOUT">
        <#if attribute.type == "PARAM">${child.id}LayoutParams<#elseif attribute.type == "LAYOUT">${child.id}</#if>.${attribute.value};
        </#if>
        </#list>
        <#if child.hasChildren>
        this.addView(${parent}, ${parent}LayoutParams);
        <#assign parent = child.id>
        <#else>
        ${parent}.addView(${child.id}, ${child.id}LayoutParams);
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

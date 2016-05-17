package ${package};

import android.content.Context;
import android.util.AttributeSet;
//Todo : find full class name
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
        ${rootLayout.layoutParams.name} ${rootLayout.id}LayoutParams = new ${rootLayout.layoutParams.name}(${rootLayout.layoutParams.width}, ${rootLayout.layoutParams.height}<#if rootLayout.layoutParams.weight??>, ${rootLayout.layoutParams.weight}</#if>);
        <#if rootLayout.layoutParams.margins??>
        ${rootLayout.id}LayoutParams.setMargins(${rootLayout.layoutParams.margins[0]}, ${rootLayout.layoutParams.margins[1]}, ${rootLayout.layoutParams.margins[2]}, ${rootLayout.layoutParams.margins[3]});
        </#if>
        <#if rootLayout.layoutParams.padding??>
        setPadding(${rootLayout.layoutParams.padding[0]}, ${rootLayout.layoutParams.padding[1]}, ${rootLayout.layoutParams.padding[2]}, ${rootLayout.layoutParams.padding[3]});
        </#if>
        <#list rootLayout.layoutParamsList?keys as key>
        set${key}(<#if !rootLayout.layoutParamsList[key].value?is_number && !rootLayout.layoutParamsList[key].number>"</#if>${rootLayout.layoutParamsList[key].value}<#if !rootLayout.layoutParamsList[key].value?is_number && !rootLayout.layoutParamsList[key].number>"</#if>);
        </#list>
        this.setLayoutParams(${rootLayout.id}LayoutParams);
        <#assign parent = "this">
        <#list rootLayout.children as child>
        ${child.id} = new ${child.name}(getContext());
        ${child.layoutParams.name} ${child.id}LayoutParams = new ${child.layoutParams.name}(${child.layoutParams.width}, ${child.layoutParams.height}<#if child.layoutParams.weight??>, ${child.layoutParams.weight}</#if>);
        <#if child.layoutParams.margins??>
        ${child.id}LayoutParams.setMargins(${child.layoutParams.margins[0]}, ${child.layoutParams.margins[1]}, ${child.layoutParams.margins[2]}, ${child.layoutParams.margins[3]});
        </#if>
        <#if child.layoutParams.padding??>
        setPadding(${child.layoutParams.padding[0]}, ${child.layoutParams.padding[1]}, ${child.layoutParams.padding[2]}, ${child.layoutParams.padding[3]});
        </#if>
        <#list child.layoutParamsList?keys as key>
        <#if child.layoutParamsList[key].paramValue>${child.id}LayoutParams<#else>${child.id}</#if>.<#if child.layoutParamsList[key].rule>addRule(${key}<#if child.layoutParamsList[key].value != "true">,${child.layoutParamsList[key].value}</#if>)<#elseif !child.layoutParamsList[key].setter>set${key}(<#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if>${child.layoutParamsList[key].value}<#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if>)<#else>${key} = <#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if>${child.layoutParamsList[key].value}<#if !child.layoutParamsList[key].value?is_number && !child.layoutParamsList[key].number>"</#if></#if>;
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

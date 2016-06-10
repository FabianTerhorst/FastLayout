# FastLayout
###Generates a Java Object for your xml layout to reduce inflate time to zero

<a href="http://www.methodscount.com/?lib=io.fabianterhorst%3Afastlayout%3A%2B"><img src="https://img.shields.io/badge/Methods and size-12 | 4 KB-e91e63.svg"></img></a>

####This projects methodcount is very low, because everything happens on compile time.

###Add the dependencies
main build.gradle
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        ...
		classpath 'io.fabianterhorst:fastlayout-gradle-plugin:0.0.2-alpha35'
		...
	}
}
```
app build.gradle
```groovy
...
apply plugin: 'fastlayout'
...
```

###First create your layout java class
This is needed to filter the layouts you want to compile
```java
//this is compiling all layouts in layout folder
@Layouts(all = true, exclude = {R.layout.sample_item})
//there you can specify the layouts
//@Layouts(layouts = {"activity_main", "fragment_one"})
//@Layouts(ids = {R.layout.activity_main, R.layout.fragment_one})
//this is needed when you want to use the class fields
//@Layouts
public class AppLayouts {
}
```
###Now use the layout inside your Activity for example
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //when you don´t wanna use the cache you can also just initiate the object
        //setContentView(new ActivityMainLayout(this));
        //The cache is reusing the object to improve the performance
        ActivityMainLayout layout = LayoutCache.getInstance(this).getLayout(LayoutCache.Activity_Main_Layout);
        setContentView(layout);
    }
}
```
###Limitations
##Specify a id for every view, otherwise the id is generated and can change.

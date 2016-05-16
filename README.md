# FastLayout
###Generates a Java Object for your xml layout to reduce inflate time to zero

####The project methodcount is 1, because everythink happen on compile time.

###Add the dependencies
main build.gradle
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        ...
		classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
		...
	}
}
```
app build.gradle
```groovy
apply plugin: 'com.android.application'
...
apply plugin: 'com.neenbedankt.android-apt'
...
dependencies {
	...
    apt 'io.fabianterhorst:fastlayout-processor:0.0.1-alpha7'
    compile 'io.fabianterhorst:fastlayout-annotations:0.0.1-alpha7'
    compile 'io.fabianterhorst:fastlayout:0.0.1-alpha7'
    ...
}
```

###First create your layout java class
This is needed to filter the layouts you want to compile
```java
//this is compiling all layouts in layout folder
@Layouts(all = true)
//there you can specify the layouts
//@Layouts(layouts = {"activity_main", "fragment_one"})
//@Layouts(ids = {R.layout.activity_main, R.layout.fragment_one})
//this is needed when you want to use the class fields
//@Layouts
public class AppLayouts {

	//The fieldName here will only be used to specify the layout object name
    //@Layout("activity_main")
    //ILayout ActivityMain;

    //when you use @Layouts you don´t need to add the @Layout annotation, the fieldName will be used
    //ILayout activity_main;
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
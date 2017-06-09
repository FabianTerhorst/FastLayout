package io.fabianterhorst.fastlayout.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Default
        //setContentView(new ActivityMainLayout(this));
        //The cache is reusing the object to improve the performance
        ActivityMainLayout layout = LayoutCache.getInstance().getLayout(this, LayoutCache.Activity_Main_Layout);
        try {
            Log.d("super", Class.forName("android.support.v7.widget.CardView").getSuperclass().getSimpleName());
        }catch (ClassNotFoundException ignore){

        }
        try {
            /*
            *
            * XmlPullParser parser = resources.getXml(R.style.MyButtonStyle);
AttributeSet attributes = Xml.asAttributeSet(parser);
Button button = new Button (getActivity(), attributes, R.style.MyButtonStyle);
            *
            * */
            //XmlBlockGetter.get2();
            //XmlBlockGetter.get();

            /*XmlPullParser parser = new AttributeSetParser();
            AttributeSet attrs = Xml.asAttributeSet(parser);

            Constructor assertConstructor = AssetManager.class.getDeclaredConstructor(Boolean.class);
            assertConstructor.setAccessible(true);
            AssetManager asserts = (AssetManager) assertConstructor.newInstance(true);
            //asserts.makeStringBlocks(false);
            Constructor xmlBlockConstructor = Class.forName("android.content.res.XmlBlock").getDeclaredConstructor(Object.class,int.class);
            xmlBlockConstructor.setAccessible(true);
            int xmlBlockInt = asserts.getClass().getMethod("openXmlAssetNative", new Class[]{int.class, String.class}).invoke(cookie, fileName);
            Object xmlBlock = xmlBlockConstructor.newInstance(asserts, xmlBlockInt);



            Constructor constructor = Class.forName("android.content.res.XmlBlock").getDeclaredConstructor(byte[].class);
            constructor.setAccessible(true);
            Class.forName("android.content.res.XmlBlock$Parser").cast(attrs);
            //asserts.getClass().getMethod("retrieveAttributes", new Class[]{});
            Object xmlBlock = constructor.newInstance(new byte[]{0});*/

            //XmlBlock.Parser myParser = new XmlBlock.Parser();
            //Class xmlBlock = Class.forName("android.content.res.XmlBlock");
            //Class block = Class.forName("android.content.res.XmlBlock$Parser");
            //Constructor<?> constructor = block.getConstructor(Long.class, xmlBlock);
            //Object instance = constructor.newInstance(0, xmlBlock.newInstance());
            //Class name = forName("android.content.res.XmlBlock$Parser");//.cast(myParser);
            //Object block = name.getDeclaredConstructors()[0].newInstance();
            //new TextView(this, (XmlBlock.Parser) name.cast(name.newInstance()));
            //Log.d("name", name.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TextView textView = new TextView(this, new XmlBlock.Parser2());
        //layout.addView(textView);
        setContentView(layout);
        //ConstraintLayout constraintLayout;
        //AttributeSet attributeSet;
        //TextView textView:
        //Todo : sort attributes for only one list for constructors
        //Todo : support for api check
        //Todo : create viewgroup converter
        //Todo : remove layout prefix
        //Todo : improve converter api (pull request?)
    }
}

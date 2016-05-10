package io.fabianterhorst.fastlayout.sample;

import io.fabianterhorst.fastlayout.annotations.Layout;

/**
 * Created by fabianterhorst on 09.05.16.
 */
@Layout(value = "<RelativeLayout\n" +
        "    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
        "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
        "    android:layout_width=\"match_parent\"\n" +
        "    android:id=\"@+id/relativeLayout1\"\n" +
        "    android:layout_height=\"match_parent\"\n" +
        "    android:paddingBottom=\"@dimen/activity_vertical_margin\"\n" +
        "    android:paddingLeft=\"@dimen/activity_horizontal_margin\"\n" +
        "    android:paddingRight=\"@dimen/activity_horizontal_margin\"\n" +
        "    android:paddingTop=\"@dimen/activity_vertical_margin\"\n" +
        "    android:layout_marginLeft=\"@dimen/activity_horizontal_margin\"\n" +
        "    android:layout_marginRight=\"@dimen/activity_horizontal_margin\"\n" +
        "    tools:context=\"io.fabianterhorst.fastlayout.sample.MainActivity\">\n" +
        "\n" +
        "    <TextView\n" +
        "        android:layout_width=\"wrap_content\"\n" +
        "        android:layout_height=\"wrap_content\"\n" +
        "        android:id=\"@+id/textView1\"\n" +
        "        android:text=\"Hello World!\"/>\n" +
        "\n" +
        "    <LinearLayout\n" +
        "        android:id=\"@+id/linearLayout1\"\n" +
        "        android:layout_width=\"match_parent\"\n" +
        "        android:layout_height=\"match_parent\">\n" +
        "\n" +
        "        <TextView\n" +
        "            android:id=\"@+id/textView2\"\n" +
        "            android:layout_width=\"match_parent\"\n" +
        "            android:layout_height=\"match_parent\"/>\n" +
        "\n" +
        "    </LinearLayout>\n" +
        "</RelativeLayout>")
public class ActivityMain {
}

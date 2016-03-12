package com.example.jon.draw_30.Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import dalvik.system.DexClassLoader;
import mygraph.dynamic.inter.Graph;


/**
 * Created by jon on 2016/3/11.
 */
public class ClassLoaderUtils {

    public static Graph getGraph(Context context,String name,int i){

        String dexPath = Environment.getExternalStorageDirectory().toString() + File.separator +"dynamic_temp.jar";
        File dexOutPath = context.getDir("dex", 0);

        File file = new File(dexPath);
        DexClassLoader cl = new DexClassLoader(file.getAbsolutePath(),dexOutPath.getAbsolutePath(),null,context.getClassLoader());
        try {
            Class libProviderClazz = cl.loadClass("mygraph.dynamic.inter."+name);

            //mygraph.dynamic.inter.rect
            Graph tempgraph = (Graph)libProviderClazz.getConstructor(float.class,float.class,float.class,float.class).newInstance(1,1,1,1);

            Log.d("data","是当前加载进来的______---------");
            return tempgraph;

        } catch (Exception e) {
            Log.d("data", "cuole++++++]]]]]]]]]]]]]]]");
            Log.d("data",e.toString());
            e.printStackTrace();
            Log.d("data", "cuole++++++]]]]]]]]]]]]]]]");
        }


        return null;
    }

    public static Graph getGraph(Context context,String name) {
        Graph tempGraph = null;
        try {
            Class c = Class.forName("mygraph.dynamic.inter."+name);
            tempGraph = (Graph)c.getConstructor(float.class,float.class,float.class,float.class).newInstance(1,1,1,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempGraph;
    }

}

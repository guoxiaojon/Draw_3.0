package com.example.jon.draw_30.Utils;

import android.os.Environment;
import android.util.Log;

import com.example.jon.draw_30.DrawView.DrawView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import mygraph.dynamic.inter.Graph;


/**
 * Created by jon on 2016/3/12.
 */
public class FileUtils {

    public static String ROOT = Environment.getExternalStorageDirectory() + File.separator;

    public static void save(List<Graph> lists) {
        File dir = new File(ROOT + "pic_cache");
        dir.mkdirs();
        ObjectOutputStream oos = null;
        for (int i = 0; i < lists.size(); i++) {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(ROOT + "pic_cache" + File.separator + "graph_" + i));
                oos.writeObject(lists.get(i));
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void open() {
        File file = null;

        ObjectInputStream ois = null;
        DrawView.mGraphLists.clear();
        for (int i = 0; i < 100; i++) {
            file = new File(ROOT + "pic_cache" + File.separator + "graph_" + i);
            if (!file.exists())
                break;

            try {
                ois = new ObjectInputStream(new FileInputStream(ROOT + "pic_cache" + File.separator + "graph_" + i));
                Graph graph = (Graph) ois.readObject();
                DrawView.mGraphLists.add(graph);
                Log.d("data", DrawView.mGraphLists.size() + "");

            } catch (Exception e) {
                Log.d("data", "=======+++++++======");

                Log.d("data", e.toString());
                Log.d("data", "=======+++++++======");
            }
        }

        try {
            if (ois != null)
                ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear() {
        File dir = new File(ROOT + "pic_cache");
        dir.mkdirs();
        for (int i = 0; i < 1000; i++) {
            File file = new File(ROOT + "pic_cache" + File.separator + "graph_" + i);
            if (file.exists())
                file.delete();
            else
                break;

        }


    }

}

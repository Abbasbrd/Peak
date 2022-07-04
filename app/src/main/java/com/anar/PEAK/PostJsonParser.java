package com.anar.PEAK;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

public class PostJsonParser {



    //used
    public static JSONObject postToJsonObject_Array(Post post) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("postCode", post.getPostCode());
            jsonObject.put("postCap", post.getPostCapacity());
            jsonObject.put("dateCreated", post.getDateCreated());
            jsonObject.put("timeCreated", post.getTimeCreated());
//                jsonObject.put("postLocation", post.getPostLocation());

            PostVoltage post_voltage = post.getVoltage();
            if (post_voltage != null) {
                JSONArray v_array = new JSONArray();
                v_array.put(post_voltage.getTR());
                v_array.put(post_voltage.getST());
                v_array.put(post_voltage.getRS());
                v_array.put(post_voltage.getRN());
                v_array.put(post_voltage.getTN());
                v_array.put(post_voltage.getSN());
                jsonObject.put("postVoltage", v_array);
            }

            PostLoad post_load = post.getLoad();
            if (post_load != null) {
                JSONArray array = new JSONArray();
                array.put(post_load.getPhaseR());
                array.put(post_load.getPhaseS());
                array.put(post_load.getPhaseT());
                array.put(post_load.getNutralN());
                jsonObject.put("postLoad", array);
            }

            List<FeederLoad> feeders_load_list = post.getLoadList();
            if (feeders_load_list != null) {
                for (int ittr = 0; ittr < feeders_load_list.size(); ittr++) {
                    JSONArray array = new JSONArray();
                    array.put(feeders_load_list.get(ittr).getPhaseR());
                    array.put(feeders_load_list.get(ittr).getPhaseS());
                    array.put(feeders_load_list.get(ittr).getPhaseT());
                    array.put(feeders_load_list.get(ittr).getNutralN());
                    jsonObject.put("F" + (ittr + 1), array);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Post parseJsonPost_Array(String jsonString, Context context) {
        Post post = new Post();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            // if you add any item such address, location & etc bellow then you should edit the other_elements_count
            post.setPostCode(jsonObject.getString("postCode"));
            post.setPostCapacity(jsonObject.getInt("postCap"));
            post.setDateCreated(jsonObject.getString("dateCreated"));
            post.setTimeCreated(jsonObject.getString("timeCreated"));

            JSONArray postVoltage_array = jsonObject.getJSONArray("postVoltage");
            int c = 0;
            int TR = postVoltage_array.getInt(c); c+=1;
            int ST = postVoltage_array.getInt(c); c+=1;
            int RS = postVoltage_array.getInt(c); c+=1;
            int RN = postVoltage_array.getInt(c); c+=1;
            int SN = postVoltage_array.getInt(c); c+=1;
            int TN = postVoltage_array.getInt(c); c+=1;

            post.setVoltage(new PostVoltage(TR, ST, RS, RN, SN, TN));

            JSONArray postLoad_array = jsonObject.getJSONArray("postLoad");
            int k = 0;
            int R = postLoad_array.getInt(k); k+=1;
            int S = postLoad_array.getInt(k);k+=1;
            int T = postLoad_array.getInt(k);k+=1;
            int N = postLoad_array.getInt(k);k+=1;

            post.setLoad(new PostLoad(R, S, T, N));

            List<FeederLoad> feederLoad = new ArrayList<>();
            post.setLoadList(feederLoad);
            int obj_count = jsonObject.length();
//            Toast.makeText(context, "json count: "+obj_count, Toast.LENGTH_SHORT).show();
            // if you added any item such address, location & etc above then you should edit the other_elements_count
            int other_elements_count = 6;
            if (obj_count > other_elements_count) {
                int feedrer_count = obj_count - other_elements_count;
                for (int ind = 1; ind <= feedrer_count; ind++) {
                    JSONArray feederLoadarray = jsonObject.getJSONArray("F" + ind);
                    int r = feederLoadarray.getInt(0);
                    int s = feederLoadarray.getInt(1);
                    int t = feederLoadarray.getInt(2);
                    int n = feederLoadarray.getInt(3);
                    FeederLoad pl = new FeederLoad(r, s, t, n);
                    feederLoad.add(pl);
//                    Toast.makeText(context, "f_R : "+r, Toast.LENGTH_SHORT).show();

                }

            }

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return post;
    }




    // used
    public static void writeJsonObject(JSONObject json, String name, Context context) {

        try {

            FileOutputStream fos = context.openFileOutput(name, MODE_PRIVATE);
            fos.write(json.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJsonObject(JSONArray json, String name, Context context) {

        try {

            FileOutputStream fos = context.openFileOutput(name, MODE_PRIVATE);
            fos.write(json.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //used
    public static String readJsonObject(String name, Context context) {
        String ret = "";
        InputStream inputStream = null;
        try {
            inputStream = context.openFileInput(name);
            boolean b;
            if (b = inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                ret = stringBuilder.toString();
            }

//            Toast.makeText(context, "inptStream is null? : " + !b + " \n" + ret, Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String readJsonObject(File file, Context context) {
        String ret = "";
        InputStream inputStream = null;
        try {
            inputStream = context.openFileInput("file");
            boolean b;
            if (b = inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                ret = stringBuilder.toString();
            }
//            Toast.makeText(context, "inptStream is null:" + !b + " \n" + ret, Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static void writeJsonArray(JSONObject json, String name, Context context) {

        try {

            FileOutputStream fos = context.openFileOutput(name, Context.MODE_APPEND);
            fos.write(json.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray readJsonArray(String name, Context context) {
        String ret = "";
        InputStream inputStream = null;
        JSONArray json = null;
        try {
            inputStream = context.openFileInput(name);
            boolean b;
            if (b = inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                ret = stringBuilder.toString();
            }
//            Toast.makeText(context, "inptStream is null:" + b + " \n" + ret, Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            json = new JSONArray(ret);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

}

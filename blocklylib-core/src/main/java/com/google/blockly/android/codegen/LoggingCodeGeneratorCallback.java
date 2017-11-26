/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.google.blockly.android.codegen;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple generator callback that logs the generated code to device Log and a Toast.
 */
public class LoggingCodeGeneratorCallback implements CodeGenerationRequest.CodeGeneratorCallback {
    protected final String mTag;
    protected final Context mContext;

    public LoggingCodeGeneratorCallback(Context context, String loggingTag) {
        mTag = loggingTag;
        mContext = context;
    }

    @Override
    public void onFinishCodeGeneration(String generatedCode) {
        // Sample callback.
        if (generatedCode.isEmpty()) {
            Toast.makeText(mContext,
                    "Something went wrong with code generation.", Toast.LENGTH_LONG).show();
        } else {
            Log.d(mTag, "test: " + generatedCode);
            Toast.makeText(mContext, generatedCode, Toast.LENGTH_LONG).show();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
            Date now = new Date();
            String fileName = formatter.format(now) + ".ino";//like 2016_01_12.txt
            try
            {
                File root = new File(Environment.getExternalStorageDirectory()+
                        File.separator+"Apni Pathshala", "INO Files");
                //File root = new File(Environment.getExternalStorageDirectory(), "Notes");
                if (!root.exists())
                {
                    root.mkdirs();
                }
                File gpxfile = new File(root, fileName);


                FileWriter writer = new FileWriter(gpxfile,true);
                writer.append(generatedCode+"\n\n");
                writer.flush();
                writer.close();
                Toast.makeText(mContext, "Data has been written to Ino File", Toast.LENGTH_SHORT).show();
            }
            catch(IOException e)
            {
                e.printStackTrace();

            }
        }
    }

}

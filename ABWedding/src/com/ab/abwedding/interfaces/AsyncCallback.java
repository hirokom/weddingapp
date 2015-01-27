/**
 * sync callback interface
 * 
 * @author ishikawatatsuya
 *
 */
package com.ab.abwedding.interfaces;

public interface AsyncCallback {

    /** this function is called previous sync execution */
    void onPreExecute();
    /** this function is called when sync execution is success */
    void onPostExecute(String result);
    /** this function is called during execution */
    void onProgressUpdate(int progress);
    /** this function is called when sync execution is canceled */
    void onCancelled();
    
}
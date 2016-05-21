package com.gallery.sync;

import com.firebase.client.Firebase;

/**
 * Created by bjz on 5/21/2016.
 */
public class FireBase {
    private static Firebase mFirebaseRef;
    private static final String FIREBASEURL= "https://glowing-fire-5858.firebaseio.com/";
    static Firebase getFirebase(){
        if(mFirebaseRef != null ){
            return mFirebaseRef;
        }else
        {
            mFirebaseRef=new Firebase(FIREBASEURL);
            return mFirebaseRef;
        }
    }

}

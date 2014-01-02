
package com.twitter.university.android.tagview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.twitter.university.android.tagview.widget.TagView;


public class TagViewActivity extends Activity {

    private static class TagAnimation implements View.OnClickListener, Animation.AnimationListener {
        private final Context ctxt;
        private final Animation animIn;
        private final Animation animOut;
        private volatile View animatedView;
        private volatile boolean finishing;

        TagAnimation(Context ctxt) {
            this.ctxt = ctxt;

            this.animOut = AnimationUtils.loadAnimation(ctxt, R.anim.tag_animate_out);
            animOut.setAnimationListener(this);

            this.animIn = AnimationUtils.loadAnimation(ctxt, R.anim.tag_animate_in);
            animIn.setAnimationListener(this);
        }

        @Override
        public void onClick(View view) {
            finishing = false;
            animatedView = view;
            animatedView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            animatedView.startAnimation(animOut);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (finishing) {
                animatedView.setLayerType(View.LAYER_TYPE_NONE, null);
                animatedView = null;
            }
            else {
                finishing = true;
                animatedView.startAnimation(animIn);
            }
        }

        @Override
        public void onAnimationStart(Animation animation) { }

        @Override
        public void onAnimationRepeat(Animation animation) { }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_view);

        TagView tags = ((TagView) findViewById(R.id.tags));

        tags.setOnClickListener(new TagAnimation(this));

        tags.setTag("One", 0);
        tags.setTag("Thirteen Twenty-three Thirty-three Forty-three Fifty-three Sixty-three Seventy-three Eighty-three Ninty-three One oh three", 1);
//        tags.addTag("Two", 0);
//        tags.addTag("Three", 0);
//        tags.addTag("Four", 0);
//        tags.addTag("Five", 0);
//        tags.addTag("Six", 1);
//        tags.addTag("Seven", 0);
//        tags.addTag("Eight", 0);
//        tags.addTag("Nine", 1);
//        tags.addTag("Ten", 0);
//        tags.addTag("Eleven", 0);
//        tags.addTag("Twelve", 0);
//        tags.addTag("Fourteen", 0);
//        tags.addTag("Fifteen", 0);
//        tags.addTag("Sixteen", 0);
    }
}

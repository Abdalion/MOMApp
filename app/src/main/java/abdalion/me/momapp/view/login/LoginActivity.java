package abdalion.me.momapp.view.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import abdalion.me.momapp.R;

public class LoginActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                String msg = "Welcome " + session.getUserName() + "!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                handleTwitterSession(Twitter.getSessionManager().getActiveSession());
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
/*                twitterAuthClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        user.updateEmail(result+"").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Firebase", "Cambio de mail exitoso");
                                }
                                else
                                    Log.d("Firebase", "Shit wasnt successfull");
                            }
                        });
                    }

                    @Override
                    public void failure(TwitterException exception) {
                    }
                });*/

            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    private void handleTwitterSession(TwitterSession session) {

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("Tw", "signInWithCredential:onComplete:" + task.isSuccessful());

                        Log.e("", "user:" + task.getResult().getUser().getUid() + "  :  " +  task.getResult().getUser().getDisplayName() + " : "
                                +  task.getResult().getUser().getEmail() + " :  "+  task.getResult().getUser().getPhotoUrl());

                        if (!task.isSuccessful()) {
                            Log.e("Tw", "signInWithCredential", task.getException());
                        }
                    }
                });
    }

}

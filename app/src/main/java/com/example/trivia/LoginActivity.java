package com.example.trivia;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity"; // Logger Tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: LoginActivity started.");

        // Ensure fragmentContainer exists
        if (findViewById(R.id.fragmentContainer) != null) {
            Log.d(TAG, "onCreate: fragmentContainer found.");

            // Load SigninFragment as the default screen if no saved state exists
            if (savedInstanceState == null) {
                Log.d(TAG, "onCreate: No saved instance state, loading SigninFragment.");
                loadFragment(new SignInFragment()); // Load Signin screen first
            } else {
                Log.d(TAG, "onCreate: Saved instance state exists, fragment not replaced.");
            }
        } else {
            Log.e(TAG, "onCreate: ERROR - fragmentContainer not found in activity_login.xml!");
        }
    }

    // Method to Replace Fragments Dynamically
    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            Log.d(TAG, "loadFragment: Attempting to load fragment: " + fragment.getClass().getSimpleName());
            try {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.commitAllowingStateLoss(); // Prevents crashes due to state loss
                Log.d(TAG, "loadFragment: Fragment successfully loaded.");
            } catch (Exception e) {
                Log.e(TAG, "loadFragment: ERROR loading fragment", e);
            }
        } else {
            Log.e(TAG, "loadFragment: ERROR - Fragment is null!");
        }
    }

    // Method to switch between SigninFragment and SignupFragment
    public void switchFragment(boolean showSignin) {
        if (showSignin) {
            loadFragment(new SignInFragment());
        } else {
            loadFragment(new SignupFragment());
        }
    }
}
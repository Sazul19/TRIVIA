package com.example.trivia;
import static com.example.trivia.R.id.btnSignUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignInFragment extends Fragment {

    private TextInputLayout emailInputLayout, passwordInputLayout;
    private TextInputEditText emailField, passwordField;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        // Initialize views
        emailInputLayout = view.findViewById(R.id.emailInputLayout);
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout);
        emailField = view.findViewById(R.id.emailField);
        passwordField = view.findViewById(R.id.passwordField);
        View btnSignIn = view.findViewById(R.id.btnSignIn);
        View btnCreateAccount = view.findViewById(R.id.btnGoToSignIn);
        View forgotPassword = view.findViewById(R.id.textViewForgotPassword);

        // Sign In Button Click
        btnSignIn.setOnClickListener(v -> attemptSignIn());

        // Create Account Click
        btnCreateAccount.setOnClickListener(v -> showCreateAccount());

        // Forgot Password Click
        forgotPassword.setOnClickListener(v -> handleForgotPassword());

        return view;
    }

    private void attemptSignIn() {
        // Reset errors
        emailInputLayout.setError(null);
        passwordInputLayout.setError(null);

        // Get values
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        // Validate inputs
        if (!isEmailValid(email)) {
            emailInputLayout.setError("Invalid email address");
            return;
        }

        if (password.isEmpty()) {
            passwordInputLayout.setError("Password cannot be empty");
            return;
        }

        // TODO: Implement actual sign-in logic
        performSignIn(email, password);
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void performSignIn(String email, String password) {
        // This is where you would implement your actual sign-in logic
        // For now, just show a success message
        Toast.makeText(requireContext(), "Sign In Successful", Toast.LENGTH_SHORT).show();

        // TODO: Add your authentication logic here
        // Example: Firebase Auth, API call, etc.
    }

    private void showCreateAccount() {
        // Navigate to sign-up fragment
        Toast.makeText(requireContext(), "Navigate to Sign Up", Toast.LENGTH_SHORT).show();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new SignupFragment())  // Replace with your container ID
                .addToBackStack(null)  // Add to back stack so user can navigate back
                .commit();
    }

    private void handleForgotPassword() {
        // Handle forgot password flow
        Toast.makeText(requireContext(), "Forgot Password Clicked", Toast.LENGTH_SHORT).show();
        // TODO: Implement forgot password logic
    }
}
package com.example.trivia;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SignupFragment extends Fragment {

    private EditText usernameField, emailField, passwordField;
    private Button btnSignUp, btnGoToSignIn;
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;
    private String currentUserEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Firebase instances
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();

        // Initialize views
        usernameField = view .findViewById(R.id.usernameField);
        emailField = view.findViewById(R.id.emailField);
        passwordField = view.findViewById(R.id.passwordField);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnGoToSignIn = view.findViewById(R.id.btnGoToSignIn);

        btnSignUp.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String username = usernameField.getText().toString().trim();

            if (validateInputs(username, email, password)) {
                signUpUser (username, email, password);
            }
        });
        btnGoToSignIn.setOnClickListener(v -> showSignin());
    }

    private boolean validateInputs(String username, String email, String password) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Additional email format validation can be added here
        return true;
    }

    private void signUpUser (String username, String email, String password) {
        // Reset errors
        usernameField.setError(null);
        emailField.setError(null);
        passwordField.setError(null);

//        mAuth.createUser WithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                FirebaseUser  user = mAuth.getCurrentUser ();
//                if (user != null) {
//                    currentUser Email = email;
//                    storeUser Data(username, email);
//                }
//            } else {
//                Toast.makeText(getContext(), "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

//    private void storeUser Data(String username, String email) {
//        Map<String, Object> userData = new HashMap<>();
//        userData.put("username", username);
//        userData.put("email", email);
//        userData.put("verified", false);
//
//        db.collection("users").document(mAuth.getCurrentUser ().getUid())
//                .set(userData)
//                .addOnSuccessListener(aVoid -> {
//                    String otp = generateOtp();
//                    storeOtpInFirestore(email, otp);
//                });
//    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void storeOtpInFirestore(String email, String otp) {
        Map<String, Object> otpData = new HashMap<>();
        otpData.put("otp", otp);
        otpData.put("timestamp", System.currentTimeMillis());

//        db.collection("otps").document(email)
//                .set(otpData)
//                .addOnSuccessListener(aVoid -> {
//                    sendVerificationEmail(email, otp);
//                    showOtpDialog(email);
//                });
    }

    private void sendVerificationEmail(String email, String otp) {
        // TODO: Implement email sending logic here
        Log.d("SignupFragment", "OTP sent to " + email + ": " + otp);
    }

    private void showOtpDialog(String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Verify OTP");
        builder.setMessage("Enter the OTP sent to your email");

        final EditText otpInput = new EditText(requireContext());
        otpInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(otpInput);

        builder.setPositiveButton("Verify", (dialog, which) -> {
            String enteredOtp = otpInput.getText().toString().trim();
            verifyOtp(email, enteredOtp);
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void verifyOtp(String email, String enteredOtp) {
//        db.collection("otps").document(email)
//                .get()
//                .addOnSuccessListener(documentSnapshot -> {
//                    if (documentSnapshot.exists()) {
//                        String storedOtp = documentSnapshot.getString("otp");
//                        long timestamp = documentSnapshot.getLong("timestamp");
//                        if (storedOtp != null && storedOtp.equals(enteredOtp) && (System.currentTimeMillis() - timestamp) < 600000) {
//                            markUser AsVerified();
//                        } else {
//                            Toast.makeText(requireContext(), "Invalid or expired OTP", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(requireContext(), "OTP not found", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

//    private void markUser AsVerified() {
//        db.collection("users").document(mAuth.getCurrentUser ().getUid())
//                .update("verified", true)
//                .addOnSuccessListener(aVoid -> {
//                    Toast.makeText(requireContext(), "Verification successful!", Toast.LENGTH_SHORT).show();
//                    // Optionally navigate to another screen
//                });
//    }
private void showSignin() {
    // Navigate to sign-in fragment

    requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, new SignInFragment())  // Replace with your container ID
            .addToBackStack(null)  // Add to back stack so user can navigate back
            .commit();
}
}
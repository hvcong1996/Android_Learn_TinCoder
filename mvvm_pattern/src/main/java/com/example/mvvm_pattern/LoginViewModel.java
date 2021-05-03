package com.example.mvvm_pattern;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

public class LoginViewModel extends BaseObservable {

    private String email;
    private String password;

    // Message (type Observable)
    public ObservableField<String> message = new ObservableField<>();

    public ObservableField<Boolean> messageVisibility = new ObservableField<>();

    public ObservableField<Boolean> isLoginSuccess = new ObservableField<>();

    // Cần thêm thẻ @Bindable để có thể thực hiện mapping UI
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        // Cần thực hiện notifyPropertyChanged để update data
        notifyPropertyChanged(BR.email);
    }

    // Cần thêm thẻ @Bindable để có thể thực hiện mapping UI
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

        // Cần thực hiện notifyPropertyChanged để update data
        notifyPropertyChanged(BR.password);
    }

    public void onClickLogin(){
        // setting Visibility for message
        messageVisibility.set(true);

        User user = new User(getEmail(), getPassword());

        if(user.isValidEmail() && user.isValidPassword()){
            message.set("Login successful");

            isLoginSuccess.set(true);
        }
        else {
            message.set("Login fail");

            isLoginSuccess.set(false);
        }
    }
}

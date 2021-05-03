package com.example.mvp_pattern;

public class LoginPresenter {

    private ILogin iLogin;

    public LoginPresenter(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    public void Login(User user){
        if(user.isValidEmail() && user.isValidPassword()){
            iLogin.LoginSuccess();
        }
        else {
            iLogin.LoginFail();
        }
    }
}

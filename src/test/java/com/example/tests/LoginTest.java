package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class LoginTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"admin", "123456", "Login Successful"},
            {"admin", "wrongpass", "Login Failed"},
            {"", "123456", "Login Failed"},
            {"admin", "", "Login Failed"},
            {null, "123456", "Login Failed"},
            {"admin", null, "Login Failed"},
            {"user", "123456", "Login Failed"},
            // 新增测试用例
            {"admin", "123456789012345678901234567890", "Login Failed"}, // 超长密码
            {"admin", "123456\n", "Login Failed"}, // 密码带换行
            {"admin", "123456 ", "Login Failed"}, // 密码带空格
            {"admin", "123456' OR '1'='1", "Login Failed"}, // SQL注入尝试
            {"<script>", "123456", "Login Failed"}, // 用户名脚本注入
            {"管理员", "123456", "Login Failed"}, // 中文用户名
            {"admin", "密码", "Login Failed"}, // 中文密码
            {"admin", "!@#$%^&*()", "Login Failed"}, // 特殊字符密码
            {"", "", "Login Failed"}, // 用户名密码均为空
            {null, null, "Login Failed"} // 用户名密码均为null
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginDataDriven(String username, String password, String expected) {
        String actual = login(username, password);
        Assert.assertEquals(actual, expected);
    }

    // 模拟的登录逻辑
    private String login(String username, String password) {
        if (username == null || password == null) {
            return "Login Failed";
        }
        if ("admin".equals(username) && "123456".equals(password)) {
            return "Login Successful";
        }
        return "Login Failed";
    }
}

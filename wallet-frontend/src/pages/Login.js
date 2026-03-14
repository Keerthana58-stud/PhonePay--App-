import React, { useState } from "react";
import api from "../api/api";
import MessageBox from "../components/MessageBox";
import "./Login.css";

function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: ""
  });

  const [message, setMessage] = useState("");
  const [type, setType] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const res = await api.post("/auth/login", formData);

      localStorage.setItem("userId", res.data.userId);
      localStorage.setItem("upiId", res.data.upiId);

      setType("success");
      setMessage(res.data.message || "Login successful");

      setTimeout(() => {
        window.location.href = "/dashboard";
      }, 1000);

    } catch (err) {
      console.error("Login error:", err);
      console.error("Login error response:", err?.response?.data);

      setType("error");
      setMessage(
        err?.response?.data?.message ||
        err?.response?.data?.error ||
        err?.message ||
        "Login failed. Check your email and password."
      );
    }
  };

  return (
    <div className="login-page">
      <div className="login-card">
        <h2>Login</h2>

        <MessageBox type={type} message={message} />

        <form onSubmit={handleLogin}>
          <input
            type="email"
            name="email"
            placeholder="Enter email"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <input
            type="password"
            name="password"
            placeholder="Enter password"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <button type="submit">Login</button>
        </form>

        <p className="login-link-text">
          Don’t have an account?{" "}
          <span onClick={() => (window.location.href = "/signup")}>
            Signup
          </span>
        </p>
      </div>
    </div>
  );
}

export default Login;
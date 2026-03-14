import React, { useState } from "react";
import api from "../api/api";
import MessageBox from "../components/MessageBox";
import "./Signup.css";

function Signup() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    password: "",
    upiId: ""
  });

  const [message, setMessage] = useState("");
  const [type, setType] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSignup = async (e) => {
    e.preventDefault();

    try {
      console.log("Sending signup data:", formData);

      const res = await api.post("/auth/signup", formData);

      console.log("Signup success response:", res.data);

      if (res.data.userId) {
        localStorage.setItem("userId", res.data.userId);
      }

      if (res.data.upiId) {
        localStorage.setItem("upiId", res.data.upiId);
      }

      setType("success");
      setMessage(res.data.message || "Signup successful");

      setFormData({
        name: "",
        email: "",
        phone: "",
        password: "",
        upiId: ""
      });

      setTimeout(() => {
        window.location.href = "/dashboard";
      }, 1000);

    } catch (err) {
      console.error("Signup error full:", err);
      console.error("Signup error response:", err?.response);
      console.error("Signup error data:", err?.response?.data);

      setType("error");
      setMessage(
        err?.response?.data?.message ||
        err?.response?.data?.error ||
        err?.message ||
        "Signup failed"
      );
    }
  };

  return (
    <div className="signup-page">
      <div className="signup-card">
        <h2>Create Account</h2>

        <MessageBox type={type} message={message} />

        <form onSubmit={handleSignup}>
          <input
            type="text"
            name="name"
            placeholder="Enter name"
            value={formData.name}
            onChange={handleChange}
            required
          />

          <input
            type="email"
            name="email"
            placeholder="Enter email"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <input
            type="text"
            name="phone"
            placeholder="Enter phone number"
            value={formData.phone}
            onChange={handleChange}
            required
          />

          <input
            type="text"
            name="upiId"
            placeholder="Enter UPI ID"
            value={formData.upiId}
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

          <button type="submit">Signup</button>
        </form>

        <p className="signup-link-text">
          Already have an account?{" "}
          <span onClick={() => (window.location.href = "/login")}>
            Login
          </span>
        </p>
      </div>
    </div>
  );
}

export default Signup;
import React from "react";
import "./Navbar.css";

function Navbar() {

  const logout = () => {
    localStorage.removeItem("userId");
    window.location.href = "/login";
  };

  return (
    <div className="navbar">

      <div className="navbar-left">
        <h2>PhonePe Wallet</h2>
      </div>

      <div className="navbar-right">

        <button
          onClick={() => window.location.href = "/dashboard"}
          className="nav-btn"
        >
          Dashboard
        </button>

        <button
          onClick={() => window.location.href = "/send-money"}
          className="nav-btn"
        >
          Send Money
        </button>

        <button
          onClick={() => window.location.href = "/add-money"}
          className="nav-btn"
        >
          Add Money
        </button>

        <button
          onClick={logout}
          className="logout-btn"
        >
          Logout
        </button>

      </div>

    </div>
  );
}

export default Navbar;
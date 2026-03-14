import React, { useState } from "react";
import api from "../api/api";
import Navbar from "../components/Navbar";
import MessageBox from "../components/MessageBox";
import "./AddMoney.css";

function AddMoney() {
  const [amount, setAmount] = useState("");
  const [message, setMessage] = useState("");
  const [type, setType] = useState("");
  const [wallet, setWallet] = useState(null);

  const userId = localStorage.getItem("userId");

  const addMoney = async (e) => {
    e.preventDefault();

    try {
      const res = await api.post(`/wallet/${userId}/add?amount=${parseFloat(amount)}`);

      // res.data is wallet object, so store it separately
      setWallet(res.data);

      // message must be string, not object
      setType("success");
      setMessage("Money added successfully");

      setAmount("");

    } catch (err) {
      console.error("Add money error:", err);
      setType("error");
      setMessage(
        err?.response?.data?.message ||
        err?.response?.data ||
        "Failed to add money"
      );
    }
  };

  return (
    <div>
      <Navbar />

      <div className="add-money-container">
        <h2>Add Money</h2>

        <MessageBox type={type} message={message} />

        <form onSubmit={addMoney}>
          <input
            type="number"
            placeholder="Enter amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />

          <button type="submit">Add Money</button>
        </form>

        {wallet && (
          <div className="wallet-preview">
            <h3>Updated Wallet</h3>
            <p><strong>Wallet ID:</strong> {wallet.id}</p>
            <p><strong>User ID:</strong> {wallet.userId}</p>
            <p><strong>Balance:</strong> ₹ {wallet.balance}</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default AddMoney;
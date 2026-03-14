import React, { useState } from "react";
import api from "../api/api";
import Navbar from "../components/Navbar";
import MessageBox from "../components/MessageBox";
import "./SendMoney.css";

function SendMoney() {
  const [formData, setFormData] = useState({
    senderUpi: localStorage.getItem("upiId") || "",
    receiverUpi: "",
    amount: ""
  });

  const [message, setMessage] = useState("");
  const [type, setType] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSendMoney = async (e) => {
    e.preventDefault();

    try {
      const payload = {
        senderUpi: formData.senderUpi,
        receiverUpi: formData.receiverUpi,
        amount: Number(formData.amount)
      };

      const res = await api.post("/payment/send", payload);

      setType("success");
      setMessage(res.data || "Payment successful");

      setFormData({
        senderUpi: localStorage.getItem("upiId") || "",
        receiverUpi: "",
        amount: ""
      });

    } catch (err) {
      console.error("Payment error:", err);
      console.error("Payment error response:", err?.response?.data);

      setType("error");
      setMessage(
        err?.response?.data?.message ||
        err?.response?.data?.error ||
        err?.message ||
        "Payment failed"
      );
    }
  };

  return (
    <div>
      <Navbar />

      <div className="send-money-page">
        <div className="send-money-card">
          <h2>Send Money</h2>

          <MessageBox type={type} message={message} />

          <form onSubmit={handleSendMoney}>
            <input
              type="text"
              name="senderUpi"
              placeholder="Sender UPI"
              value={formData.senderUpi}
              onChange={handleChange}
              required
            />

            <input
              type="text"
              name="receiverUpi"
              placeholder="Receiver UPI"
              value={formData.receiverUpi}
              onChange={handleChange}
              required
            />

            <input
              type="number"
              name="amount"
              placeholder="Amount"
              value={formData.amount}
              onChange={handleChange}
              required
            />

            <button type="submit">Send Money</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default SendMoney;
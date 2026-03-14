import React, { useEffect, useState } from "react";
import api from "../api/api";
import Navbar from "../components/Navbar";
import WalletCard from "../components/WalletCard";
import TransactionTable from "../components/TransactionTable";
import MessageBox from "../components/MessageBox";
import "./Dashboard.css";

function Dashboard() {
  const [wallet, setWallet] = useState(null);
  const [transactions, setTransactions] = useState([]);
  const [message, setMessage] = useState("");
  const [type, setType] = useState("");

  const userId = localStorage.getItem("userId");
  const upiId = localStorage.getItem("upiId");

  useEffect(() => {
    if (!userId || !upiId) {
      setType("error");
      setMessage("Please login first");
      return;
    }

    const loadDashboardData = async () => {
      await fetchWallet();
      await fetchTransactions();
    };

    loadDashboardData();
  }, [userId, upiId]);

  const fetchWallet = async () => {
    try {
      const res = await api.get(`/wallet/${userId}`);
      setWallet(res.data);
    } catch (err) {
      setType("error");
      setMessage("Failed to fetch wallet details");
    }
  };

  const fetchTransactions = async () => {
    try {
      const res = await api.get(`/transactions/user/${upiId}`);
      setTransactions(res.data);
    } catch (err) {
      setType("error");
      setMessage("Failed to fetch transactions");
    }
  };

  return (
    <div>
      <Navbar />

      <div className="dashboard-container">
        <h2>Dashboard</h2>
        <p><strong>My UPI ID:</strong> {upiId}</p>

        <MessageBox type={type} message={message} />

        <WalletCard wallet={wallet} />

        <div className="dashboard-actions">
          <button onClick={fetchWallet}>Refresh Wallet</button>
          <button onClick={fetchTransactions}>Refresh Transactions</button>
        </div>

        <TransactionTable transactions={transactions} />
      </div>
    </div>
  );
}

export default Dashboard;
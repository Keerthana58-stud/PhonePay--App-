import React from "react";
import "./TransactionTable.css";

function TransactionTable({ transactions }) {
  return (
    <div className="transaction-table-container">
      <h3>Transaction History</h3>

      {!transactions || transactions.length === 0 ? (
        <p>No transactions found</p>
      ) : (
        <table className="transaction-table">
          <thead>
            <tr>
              <th>Sender UPI</th>
              <th>Receiver UPI</th>
              <th>Amount</th>
              <th>Status</th>
              <th>Time</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((tx) => (
              <tr key={tx.id}>
                <td>{tx.senderUpi}</td>
                <td>{tx.receiverUpi}</td>
                <td>₹ {tx.amount}</td>
                <td>{tx.status}</td>
                <td>{tx.timestamp ? tx.timestamp : "N/A"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default TransactionTable;
import React from "react";
import "./WalletCard.css";

function WalletCard({ wallet }) {
  return (
    <div className="wallet-card">
      <h3>My Wallet</h3>

      {!wallet ? (
        <p>No wallet data available</p>
      ) : (
        <div className="wallet-details">
          <p>
            <strong>Wallet ID:</strong> {wallet.id}
          </p>
          <p>
            <strong>User ID:</strong> {wallet.userId}
          </p>
          <p className="wallet-balance">
            Balance: ₹ {wallet.balance}
          </p>
        </div>
      )}
    </div>
  );
}

export default WalletCard;
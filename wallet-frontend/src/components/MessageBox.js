import React from "react";
import "./MessageBox.css";

function MessageBox({ type, message }) {
  if (!message) return null;

  return (
    <div className={`message-box ${type}`}>
      {message}
    </div>
  );
}

export default MessageBox;
import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";

/*
  Root entry point of React application
  It renders the App component inside the root div
*/

const root = ReactDOM.createRoot(
  document.getElementById("root")
);

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
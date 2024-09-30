import React, { useEffect, useState } from 'react';

const KitchenOrders = () => {
  // State to store kitchen orders
  const [orders, setOrders] = useState([]);

  // Function to fetch kitchen orders from the API
  const fetchKitchenOrders = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/orders/kitchen", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });
      const data = await response.json();
      setOrders(data);
    } catch (error) {
      console.error("Error fetching kitchen orders:", error);
    }
  };

  // Fetch orders when the component loads
  useEffect(() => {
    fetchKitchenOrders();
  }, []);

  return (
    <div className="container mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
      <h1 className="text-2xl font-bold text-center mb-6">Kitchen Orders</h1>
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white border border-gray-200">
          <thead>
            <tr className="bg-gray-100 border-b">
              <th className="py-2 px-4 border">Table Number</th>
              <th className="py-2 px-4 border">Items</th>
              <th className="py-2 px-4 border">Quantity</th>
            </tr>
          </thead>
          <tbody>
            {orders.length > 0 ? (
              orders.map((order, index) => (
                <tr key={index} className="border-b">
                  <td className="py-2 px-4 border">{order.tableNumber}</td>
                  <td className="py-2 px-4 border">{order.items}</td>
                  <td className="py-2 px-4 border">{order.quantity}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td className="py-2 px-4 border text-center" colSpan="3">
                  No orders available
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default KitchenOrders;

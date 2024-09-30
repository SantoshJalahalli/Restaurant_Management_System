import React, { useState } from 'react';

// Menu data (You can customize it according to your restaurant's menu)
const menuData = [
  { item: 'Pizza', price: 10 },
  { item: 'Pasta', price: 15 },
  { item: 'Salad', price: 5 },
  { item: 'Burger', price: 8 },
  { item: 'Sandwich', price: 6 }
];

// Change the function name to start with an uppercase letter
const OrderMenu = () => {
  const [tableNumber, setTableNumber] = useState('');
  const [order, setOrder] = useState(
    menuData.map(() => ({
      quantity: 0
    }))
  );

  // Handle quantity change for each menu item
  const handleQuantityChange = (index, quantity) => {
    const newOrder = [...order];
    newOrder[index].quantity = quantity;
    setOrder(newOrder);
  };

  // Submit order function to handle POST request
  const submitOrder = async () => {
    if (!tableNumber) {
      alert('Please enter a table number.');
      return;
    }

    // Collect ordered items with quantity greater than 0
    const orderedItems = menuData
      .map((item, index) => ({
        ...item,
        quantity: order[index].quantity
      }))
      .filter((item) => item.quantity > 0);

    if (orderedItems.length === 0) {
      alert('Please select at least one item.');
      return;
    }

    // Create the order object to send to the backend
    const orderData = {
      "tableNumber": parseInt(tableNumber),
      "items": orderedItems.map((item) => item.item).join(', '),
      "price": orderedItems.map((item) => item.price).join(', '),
      "quantity": orderedItems.map((item) => item.quantity).join(', ')
    };
console.log(JSON.stringify(orderData))
    try {
      const response = await fetch('http://localhost:8080/api/orders/place', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderData)
      });

      if (response.ok) {
        alert('Order placed successfully!');
      } else {
        alert('Failed to place order.');
      }
    } catch (error) {
      console.error('Error placing order:', error);
      alert('An error occurred while placing the order.');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="container mx-auto bg-white p-8 rounded-lg shadow-md w-full md:w-2/3 lg:w-1/2">
        <h1 className="text-2xl font-bold text-center mb-6">Restaurant Order System</h1>

        {/* Table Number Input */}
        <div className="mb-6 text-center">
          <label htmlFor="tableNumber" className="text-lg mr-2">
            Table Number:
          </label>
          <input
            type="number"
            id="tableNumber"
            className="border border-gray-300 rounded-md py-2 px-4 w-24 text-center focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
            placeholder="Enter Table Number"
            value={tableNumber}
            min="1"
            onChange={(e) => setTableNumber(e.target.value)}
            required
          />
        </div>

        {/* Menu Table */}
        <h2 className="text-xl font-semibold text-center mb-4">Menu</h2>
        <table className="w-full mb-6 border-collapse border border-gray-200">
          <thead>
            <tr className="bg-gray-200">
              <th className="py-2 border border-gray-300">Item</th>
              <th className="py-2 border border-gray-300">Price ($)</th>
              <th className="py-2 border border-gray-300">Quantity</th>
            </tr>
          </thead>
          <tbody>
            {menuData.map((menuItem, index) => (
              <tr key={index} className="text-center">
                <td className="py-2 border border-gray-300">{menuItem.item}</td>
                <td className="py-2 border border-gray-300">{menuItem.price}</td>
                <td className="py-2 border border-gray-300">
                  <input
                    type="number"
                    min="0"
                    value={order[index].quantity}
                    onChange={(e) => handleQuantityChange(index, parseInt(e.target.value))}
                    className="border border-gray-300 rounded-md py-1 px-2 w-16 text-center focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                  />
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Submit Button */}
        <div className="text-center">
          <button
            onClick={submitOrder}
            className="bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-6 rounded-md transition duration-200"
          >
            Submit Order
          </button>
        </div>
      </div>
    </div>
  );
};

export default OrderMenu;

import React from 'react';
import ReactDOM from 'react-dom';
import InventoryPage from "./inventory-page";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<InventoryPage/>, div);
  ReactDOM.unmountComponentAtNode(div);
});

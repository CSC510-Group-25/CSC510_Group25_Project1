import React from 'react';
import ReactDOM from 'react-dom';
import InventoryForm from "./inventory-form";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<InventoryForm/>, div);
  ReactDOM.unmountComponentAtNode(div);
});

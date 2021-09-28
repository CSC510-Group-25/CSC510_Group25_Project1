import React from 'react';
import ReactDOM from 'react-dom';
import InventoryTable from "./inventory-table";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<InventoryTable/>, div);
  ReactDOM.unmountComponentAtNode(div);
});

import React from 'react';
import ReactDOM from 'react-dom';
import MenuTable from "./menu-table";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<MenuTable/>, div);
  ReactDOM.unmountComponentAtNode(div);
});
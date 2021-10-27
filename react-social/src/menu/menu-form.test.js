import React from 'react';
import ReactDOM from 'react-dom';
import MenuForm from "./menu-form";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<MenuForm/>, div);
  ReactDOM.unmountComponentAtNode(div);
});
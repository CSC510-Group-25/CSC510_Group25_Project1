import React from 'react';
import ReactDOM from 'react-dom';
import MenuPage from "./menu-page";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<MenuPage/>, div);
  ReactDOM.unmountComponentAtNode(div);
});

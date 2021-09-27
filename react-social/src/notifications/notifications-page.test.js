import React from 'react';
import ReactDOM from 'react-dom';
import NotificationsPage from "./notifications-page";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<NotificationsPage/>, div);
  ReactDOM.unmountComponentAtNode(div);
});

import React from 'react';
import ReactDOM from 'react-dom';
import AnalyticsPage from "./analytics-page";

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<AnalyticsPage/>, div);
  ReactDOM.unmountComponentAtNode(div);
});

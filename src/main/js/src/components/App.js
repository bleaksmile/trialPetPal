import React from 'react';
import { Switch, Route } from 'react-router-dom';
import LandingPage from './LandingPage';
import Home from './Home';
import GoogleAuth from './login/GoogleAuth';
import PrivateRoute from './PrivateRoute';
import '../stylesheets/main.scss';
import '../stylesheets/buttons.scss';

const App = () => {
  return (
    <Switch>
      <Route exact path="/" component={LandingPage} />
      <Route path="/googleauth" component={GoogleAuth} />
      <Route path="/home" component={PrivateRoute(Home)} />
    </Switch>
  );
};

export default App;
